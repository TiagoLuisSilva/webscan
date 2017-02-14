package br.com.tls.webscan.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tls.webscan.component.EnsaioComponent;
import br.com.tls.webscan.dto.EnsaioDTO;
import br.com.tls.webscan.entity.EnsaioVO;

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
	 
		model.put("ensaio", new EnsaioDTO(ensaio));
        return  "ensaio" ; 
	}
		
}
