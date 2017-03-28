package br.com.tls.webscan.dto;

import br.com.tls.webscan.entity.DadosEnsaioVO;
import br.com.tls.webscan.entity.TipoEnsaioEnum;
 
public class DadosEnsaioDTO { 
	private Long id;   
	private String tipo; 
    private Integer valorX; 
	private Integer valorY;
	
	public DadosEnsaioDTO(){
	}
	public DadosEnsaioDTO(DadosEnsaioVO dadosEnsaio){
		this.id = dadosEnsaio.getId();
		this.tipo = dadosEnsaio.getTipo().name();
		this.valorX = dadosEnsaio.getValorX();
		this.valorY = dadosEnsaio.getValorY();
				 
	}
	
	public DadosEnsaioVO toDadosEnsaio(){
		DadosEnsaioVO dadosEnsaio = new DadosEnsaioVO();
		dadosEnsaio.setId(id);
		dadosEnsaio.setTipo(TipoEnsaioEnum.valueOf(tipo));
		dadosEnsaio.setValorX(valorX);
		dadosEnsaio.setValorY(valorY);
		
		return dadosEnsaio;
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	} 
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	/**
	 * @return the valorX
	 */
	public Integer getValorX() {
		return valorX;
	}
	/**
	 * @param valorX the valorX to set
	 */
	public void setValorX(Integer valorX) {
		this.valorX = valorX;
	}
	/**
	 * @return the valorY
	 */
	public Integer getValorY() {
		return valorY;
	}
	/**
	 * @param valorY the valorY to set
	 */
	public void setValorY(Integer valorY) {
		this.valorY = valorY;
	}
	
	
	
}
