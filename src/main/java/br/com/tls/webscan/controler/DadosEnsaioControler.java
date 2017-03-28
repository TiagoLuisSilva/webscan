	package br.com.tls.webscan.controler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.tls.webscan.component.EnsaioComponent;
import br.com.tls.webscan.dto.EnsaioDTO;
import br.com.tls.webscan.dto.FiltroEnsaios;
import br.com.tls.webscan.entity.DadosEnsaioVO;
import br.com.tls.webscan.entity.EnsaioVO;

@Controller
public class DadosEnsaioControler {

	@Autowired
	private EnsaioComponent ensaioComponent;

	@RequestMapping(value={"/dadosEnsaio"},  method=RequestMethod.GET)
	public String iniciar(Map<String, Object> model) { 
		FiltroEnsaios filtroEnsaio = new FiltroEnsaios();
		filtroEnsaio.setDataInicio(new Date());
		filtroEnsaio.setDataFinal(null);
		List<EnsaioVO> ensaios = ensaioComponent.consultar(filtroEnsaio);
 
		model.put("filtroEnsaio",filtroEnsaio);
		model.put("ensaios", ensaios);
		model.put("isDadosAtivo", true); 
		model.put("isEnsaioAtivo", false);
		return "dadosEnsaioCons";
	}

	@RequestMapping(value={"/dadosEnsaio"},  method=RequestMethod.POST)
	public String filtrar(@ModelAttribute("filtro") final  FiltroEnsaios filtroEnsaio,  BindingResult bindingResult,   ModelMap model) { 
		List<EnsaioVO> ensaios = ensaioComponent.consultar(filtroEnsaio);

		model.put("filtroEnsaio",filtroEnsaio);
		model.put("ensaios", ensaios);
		model.put("isDadosAtivo", true);
		model.put("isEnsaioAtivo", false);
		return "dadosEnsaioCons";
	}

	@RequestMapping(value={"/dadosEnsaio/novo"},  method=RequestMethod.GET)
	public String novo(ModelMap model) { 
		EnsaioDTO ensaio = new EnsaioDTO();
		ensaio.setDataDoEnsaio(new Date());
		model.addAttribute("ensaio", ensaio);
		model.put("isDadosAtivo", true);
		model.put("isEnsaioAtivo", false);
		return "dadosEnsaioForm";
	}

	@GetMapping(value={"/dadosEnsaio/{idEnsaio}"})
	public String editar(ModelMap model,  @PathVariable Long idEnsaio) { 
	    EnsaioVO ensaio =	ensaioComponent.consultarPorId(idEnsaio);

		model.put("ensaio", new EnsaioDTO(ensaio));
		model.put("isDadosAtivo", true);
		model.put("isEnsaioAtivo", false);
		return "dadosEnsaioForm";
	}

	@RequestMapping(value="/dadosEnsaio/delete/{idEnsaio}",  method=RequestMethod.DELETE)
	public String delete(ModelMap model,  @PathVariable Long idEnsaio) { 
	    EnsaioVO ensaio =	ensaioComponent.consultarPorId(idEnsaio);
	    ensaioComponent.excluir(ensaio);
		model.put("ensaio", new EnsaioDTO());
		model.put("isDadosAtivo", true);
		model.put("isEnsaioAtivo", false);
		return "dadosEnsaioForm";
	}

	@RequestMapping(value="/dadosEnsaio/gravar",  method=RequestMethod.POST)
	public String gravar(@Valid @ModelAttribute("ensaio") final  EnsaioDTO ensaio,  BindingResult bindingResult,   ModelMap model) { 
		if (bindingResult.hasErrors()){
			return "dadosEnsaioForm";
		}

		EnsaioVO ensaioVO =	 ensaio.toEnsaioVO();
		List<DadosEnsaioVO> listaDadosDoEnsaio = ensaioComponent.consultarDados(ensaio.getId());
		if  (listaDadosDoEnsaio ==null || listaDadosDoEnsaio.isEmpty()){ 
			
			ensaioVO.setDadosEnsaio(new ArrayList<DadosEnsaioVO>()); 
		} else {
			ensaioVO.setDadosEnsaio(listaDadosDoEnsaio);
		}

	    ensaioComponent.persistir(ensaioVO);
		
		model.put("mensagem", "Ensaio gravado com sucesso");
		model.put("ensaio", new EnsaioDTO(ensaioVO));
		model.put("isDadosAtivo", true);
		model.put("isEnsaioAtivo", false);
		return "dadosEnsaioForm";
	}
	

	 

	
}
