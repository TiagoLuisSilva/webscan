package br.com.tls.webscan.component;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tls.webscan.dto.FiltroEnsaios;
import br.com.tls.webscan.entity.EnsaioVO;
import br.com.tls.webscan.repository.EnsaioRepository;
import br.com.tls.webscan.uteis.Uteis;

@Component
public class EnsaioComponent   {
	@Autowired
	private EnsaioRepository ensaioRepository;
	
    @PersistenceContext
    protected EntityManager em;

    @Transactional(readOnly=true, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
    public EnsaioVO consultarPorId(Long idEnsaio){
    	
    	return ensaioRepository.findOne(idEnsaio);
    }

    @Transactional(readOnly=true, rollbackFor=Exception.class, isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRES_NEW)
    public List<EnsaioVO> consultar(FiltroEnsaios filtro){
        Criteria criteria = em.unwrap(Session.class).createCriteria(EnsaioVO.class);
        if (StringUtils.isNotBlank(filtro.getCliente())){
        	criteria.add(Restrictions.like("clienteEmpresa", filtro.getCliente() +"%"));
        }

        if (StringUtils.isNotBlank(filtro.getNumeroOS())){ 
        	criteria.add(Restrictions.eq("ordemDeServico", filtro.getNumeroOS()));
        }

        if (filtro.getDataInicio() !=null){
        	criteria.add(Restrictions.ge("dataDoEnsaio", Uteis.getData0000(filtro.getDataInicio())));
        }
        if (filtro.getDataFinal() !=null){
        	criteria.add(Restrictions.le("dataDoEnsaio", Uteis.getData2359(filtro.getDataFinal())));
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
}
