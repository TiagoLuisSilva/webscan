package br.com.tls.webscan.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategy.PascalCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import br.com.tls.webscan.entity.DadosEnsaioVO;
 
public class DadosEnsaioGravar  implements Serializable{
 
	private Long id;
	 
	private  List<DadosEnsaioDTO> dadosEnsaio;

 
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<DadosEnsaioDTO> getDadosEnsaio() {
		if (dadosEnsaio==null){
			dadosEnsaio = new ArrayList<>();
		}
		return dadosEnsaio;
	}

	public void setDadosEnsaio(List<DadosEnsaioDTO> dadosEnsaio) {
		this.dadosEnsaio = dadosEnsaio;
	}

	public List<DadosEnsaioVO> toDadosEnsaio(){
		List<DadosEnsaioVO> listaDados = new ArrayList<DadosEnsaioVO>();
		for (DadosEnsaioDTO dadoDTO : getDadosEnsaio()){
			DadosEnsaioVO dado = dadoDTO.toDadosEnsaio();
			listaDados.add(dado);
		}
		
		return listaDados;
		
	}
 
}
