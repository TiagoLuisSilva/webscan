package br.com.tls.webscan.controler;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tls.webscan.component.EnsaioComponent;
import br.com.tls.webscan.dto.DadosEnsaioGravar;
import br.com.tls.webscan.dto.EnsaioDTO;
import br.com.tls.webscan.dto.RelatorioEnsaioDTO;
import br.com.tls.webscan.entity.EnsaioVO;
import br.com.tls.webscan.uteis.Uteis;

@Controller
public class EnsaioControler {


	@Autowired
	private EnsaioComponent ensaioComponent;
	
	@RequestMapping(value={"/ensaio"},  method=RequestMethod.GET)
	public String welcome(ModelMap model ) {
		model.put("message", "Teste");
		model.put("ensaio", new EnsaioDTO());
		model.put("isDadosAtivo", false);
		model.put("isEnsaioAtivo", true);
		
		return "ensaio";
	}
	

	@RequestMapping(value="/ensaio/{idEnsaio}",  method=RequestMethod.GET)
	public String  carregar(ModelMap model,   @PathVariable Long idEnsaio) { 
		EnsaioVO ensaio = ensaioComponent.consultarPorId(idEnsaio);

		DadosEnsaioGravar ensaioGravar = new DadosEnsaioGravar(); 
		RelatorioEnsaioDTO ensaioRelatorio = new RelatorioEnsaioDTO(); 
		model.put("ensaio", new EnsaioDTO(ensaio));
		model.put("ensaioGravar", ensaioGravar);
		model.put("ensaioRelatorio", ensaioRelatorio);
        return  "ensaio" ; 
	}


	@RequestMapping(value="/ensaio/limparDados",  method=RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody String   limparDados( @RequestBody  DadosEnsaioGravar ensaioLimpar ) {
		ensaioComponent.limparDados(ensaioLimpar.getId());
		return "";
	}
	@RequestMapping(value="/ensaio/gravar",  method=RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE) 
	public @ResponseBody String   gravar( @RequestBody  DadosEnsaioGravar ensaioGravar ) {
		
		ensaioComponent.gravarDadoEnsaio(ensaioGravar.getId(), ensaioGravar.toDadosEnsaio());
	 
		
		return "";
		
	}


	@RequestMapping(value="/ensaio/download",  method=RequestMethod.POST)
	public @ResponseBody String    gerarRelatorio(  @RequestBody  RelatorioEnsaioDTO ensaioRelatorio) throws Exception {
		//String path = RelatorioEnsaioDTO.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String path = "/var/";
		
		try {
		 	File svgEspessura=new File(path+"espessura.svg");
		 	File svgTransversal=new File(path+"transversal.svg");
		 	File svgLongitudinal=new File(path+"longitudinal.svg");
		 	
		 	String headerSvg = "<?xml version=\"1.0\" standalone=\"no\"?><!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">";
		 	if (StringUtils.isNotBlank(ensaioRelatorio.getSvgEspessura())){
		 		BufferedWriter writerEspessura = new BufferedWriter(new FileWriter(svgEspessura));
		 		writerEspessura.write(headerSvg+ensaioRelatorio.getSvgEspessura());
		 		writerEspessura.close();
		     	ensaioRelatorio.setUrlEspessura(Uteis.converterSvgToJpg(path+"espessura"));
		 	}
		 	if (StringUtils.isNotBlank(ensaioRelatorio.getSvgLongitudinal())){
		 		BufferedWriter writerLongitudinal = new BufferedWriter(new FileWriter(svgLongitudinal));
		 		writerLongitudinal.write(headerSvg+ensaioRelatorio.getSvgLongitudinal());
		 		writerLongitudinal.close();
		     	ensaioRelatorio.setUrlLongitudinal(Uteis.converterSvgToJpg(path+"longitudinal"));
		 	}
		 	if (StringUtils.isNotBlank(ensaioRelatorio.getSvgTransversal())){
		 		BufferedWriter writerTransversal = new BufferedWriter(new FileWriter(svgTransversal));
		 		writerTransversal.write(headerSvg+ensaioRelatorio.getSvgTransversal());
		 		writerTransversal.close();
		     	ensaioRelatorio.setUrlTransversal(Uteis.converterSvgToJpg(path+"transversal"));
		 	}
		} catch(Exception e) {
		    e.printStackTrace();
		}
 
        
        
	    byte[] arquivo = ensaioComponent.gerarRelatorio(ensaioRelatorio);
	    FileOutputStream fileOuputStream = null;
	    try { 
	        fileOuputStream = new FileOutputStream(path+"relatorio\\Relatorio.pdf"); 
	        fileOuputStream.write(arquivo);
	     } finally {
	        fileOuputStream.close();
	     } 
	//	return Uteis.abrirPdf(arquivo, "Ensaio.pdf"); 
		 return "";
	}

	@RequestMapping(value="/ensaio/download",  method=RequestMethod.GET)
	public HttpEntity<byte[]>  gerarRelatorioRelatorio() throws Exception{ 

		String arquivo = RelatorioEnsaioDTO.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		 Path path = Paths.get(arquivo.subSequence(1, arquivo.length())+"relatorio/Relatorio.pdf");
		 byte[] data = Files.readAllBytes(path);
		 return Uteis.abrirPdf(data, "Relatorio.pdf");
		  
	}
 
	
}
