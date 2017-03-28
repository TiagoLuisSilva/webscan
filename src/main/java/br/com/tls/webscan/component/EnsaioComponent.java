package br.com.tls.webscan.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tls.webscan.dto.FiltroEnsaios;
import br.com.tls.webscan.dto.RelatorioEnsaioDTO;
import br.com.tls.webscan.entity.DadosEnsaioVO;
import br.com.tls.webscan.entity.EnsaioVO;
import br.com.tls.webscan.repository.EnsaioRepository;
import br.com.tls.webscan.uteis.ParametrosRelVO;
import br.com.tls.webscan.uteis.RelatorioUtils;
import br.com.tls.webscan.uteis.Uteis;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

@Component
public class EnsaioComponent   {
	@Autowired
	private EnsaioRepository ensaioRepository;
	
    @PersistenceContext
    protected EntityManager em;

    @Transactional(readOnly=true, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
    public EnsaioVO consultarPorId(Long idEnsaio){
    	EnsaioVO ensaio = ensaioRepository.findOne(idEnsaio);
    	if (ensaio != null && ensaio.getDadosEnsaio() !=null && !ensaio.getDadosEnsaio().isEmpty()){ 
    		Hibernate.initialize(ensaio.getDadosEnsaio());
    	}
    	return ensaioRepository.findOne(idEnsaio);
    }

    @Transactional(readOnly=true, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
    public List<EnsaioVO> consultar(FiltroEnsaios filtro){
        Criteria criteria = em.unwrap(Session.class).createCriteria(EnsaioVO.class);
     

        if (StringUtils.isNotBlank(filtro.getNumeroOS())){ 
        	criteria.add(Restrictions.eq("ordemDeServico", filtro.getNumeroOS()));
        } else {
    	    if (StringUtils.isNotBlank(filtro.getCliente())){
           	    criteria.add(Restrictions.like("clienteEmpresa", filtro.getCliente() +"%"));
            }
            if (filtro.getDataInicio() !=null){
            	criteria.add(Restrictions.ge("dataDoEnsaio", Uteis.getData0000(filtro.getDataInicio())));
            }
            if (filtro.getDataFinal() !=null){
            	criteria.add(Restrictions.le("dataDoEnsaio", Uteis.getData2359(filtro.getDataFinal())));
            }
        	
        	
        }

        List<EnsaioVO> lista =  criteria.list();
    	return lista;
    }
    
    public EnsaioVO persistir(EnsaioVO ensaio){
    	  return ensaioRepository.save(ensaio);
    }

    public void excluir(EnsaioVO ensaio){
    	   ensaioRepository.delete(ensaio);
    }

	@SuppressWarnings("unchecked")
	public List<DadosEnsaioVO> consultarDados(Long idEnsaio) {

        Criteria criteria = em.unwrap(Session.class).createCriteria(DadosEnsaioVO.class);
        criteria.createAlias("ensaio", "ensaio");
        criteria.add(Restrictions.eq("ensaio.id", idEnsaio));
        return criteria.list();
	}

    @Transactional(readOnly=false, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
	public void gravarDadoEnsaio(Long idEnsaio, List<DadosEnsaioVO> dadosEnsaio) {
		 EnsaioVO ensaio = consultarPorId(idEnsaio);
		 ensaio.setDadosEnsaio(new ArrayList<DadosEnsaioVO>());
		 ensaio.getDadosEnsaio().clear();
		 for (DadosEnsaioVO dado : dadosEnsaio) {
			 
			 dado.setEnsaio(ensaio);
			 ensaio.getDadosEnsaio().add(dado);
		 } 
		 ensaioRepository.saveAndFlush(ensaio);
	}

    @Transactional(readOnly=false, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
	public void limparDados(Long idEnsaio) {
		 
    	 EnsaioVO ensaio = consultarPorId(idEnsaio);
    	 if (ensaio !=null && ensaio.getDadosEnsaio() != null && !ensaio.getDadosEnsaio().isEmpty()){
    		 ensaio.getDadosEnsaio().clear();
    		 ensaioRepository.save(ensaio);
    	 }
		 
	}

	public byte[] gerarRelatorio(RelatorioEnsaioDTO rel) throws Exception {
	     EnsaioVO ensaio = consultarPorId(rel.getId());
	     rel.setDataDoEnsaio(ensaio.getDataDoEnsaio());
	     rel.setCodigo(ensaio.getCodigo());
	     rel.setOrdemDeServico(ensaio.getOrdemDeServico());
	     rel.setItem(ensaio.getItem());
	     rel.setClienteEmpresa(ensaio.getClienteEmpresa());
		 String  url = EnsaioComponent.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 ParametrosRelVO parametros = new ParametrosRelVO();
		 parametros.setDiretorioRel(url+"relatorio");
	     rel.setUrlLogo(parametros.getDiretorioRel()+"/logo.png");
		 parametros.getObjetos().add(rel);
		 parametros.setNomeRelatorio("Relatorio.pdf");
	    JRDataSource dataSource = null;
		File arquivo = null; 
		arquivo = new File(parametros.getDiretorioRel()+"/ImpressaoGraficos.jasper");  
		String string = parametros.getDiretorioRel().substring(1, parametros.getDiretorioRel().length());
	    InputStream jasperStream = getClass().getResourceAsStream("/relatorio/ImpressaoGraficos.jasper");
	    Map<String,Object> params = new HashMap<>();
	    dataSource = new JRBeanCollectionDataSource(parametros.getObjetos(), false);
	    JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream); 
	    params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
	 
	    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
	    
	    String caminhoRelatorio = RelatorioUtils.realizarExportacaoPDF(parametros, jasperPrint);
	    File file = new File(caminhoRelatorio);
        byte[] buffer = null;
        InputStream inputStream = null;
        if (file.exists()) {
        	try{
	            inputStream = new FileInputStream(file);
	            buffer = new byte[inputStream.available()];
	            inputStream.read(buffer);
        	}finally{
        		if (inputStream != null) {
        			inputStream.close();
        		}
        	}    
        }
        return buffer;
	}
}
