package br.com.tls.webscan.dto;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

public class RelatorioEnsaioDTO {

	private Long id;
	private String ordemDeServico; 
	private Date dataDoEnsaio; 
	private String item; 
	private String codigo; 
	private String clienteEmpresa;  
	private Double avanco; 	
	private Double comprimentoMaximo;  
	private boolean espesura; 
	private boolean longitudinal; 
	private boolean transversal;  

	private String svgEspessura;
	private String svgLongitudinal;
	private String svgTransversal;

	private String urlEspessura;
	private String urlLongitudinal;
	private String urlTransversal;
	
	private String urlLogo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSvgEspessura() {
		return svgEspessura;
	}
	public void setSvgEspessura(String svgEspessura) {
		this.svgEspessura = svgEspessura;
	}
	public String getSvgLongitudinal() {
		return svgLongitudinal;
	}
	public void setSvgLongitudinal(String svgLongitudinal) {
		this.svgLongitudinal = svgLongitudinal;
	}
	public String getSvgTransversal() {
		return svgTransversal;
	}
	public void setSvgTransversal(String svgTransversal) {
		this.svgTransversal = svgTransversal;
	}
	public String getUrlEspessura() {
		return urlEspessura;
	}
	public void setUrlEspessura(String urlEspessura) {
		this.urlEspessura = urlEspessura;
	}
	public String getUrlLongitudinal() {
		return urlLongitudinal;
	}
	public void setUrlLongitudinal(String urlLongitudinal) {
		this.urlLongitudinal = urlLongitudinal;
	}
	public String getUrlTransversal() {
		return urlTransversal;
	}
	public void setUrlTransversal(String urlTransversal) {
		this.urlTransversal = urlTransversal;
	}

	public String getOrdemDeServico() {
		return ordemDeServico;
	}
	public void setOrdemDeServico(String ordemDeServico) {
		this.ordemDeServico = ordemDeServico;
	}
	public Date getDataDoEnsaio() {
		return dataDoEnsaio;
	}
	public void setDataDoEnsaio(Date dataDoEnsaio) {
		this.dataDoEnsaio = dataDoEnsaio;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getClienteEmpresa() {
		return clienteEmpresa;
	}
	public void setClienteEmpresa(String clienteEmpresa) {
		this.clienteEmpresa = clienteEmpresa;
	}
	public Double getAvanco() {
		return avanco;
	}
	public void setAvanco(Double avanco) {
		this.avanco = avanco;
	}
	public Double getComprimentoMaximo() {
		return comprimentoMaximo;
	}
	public void setComprimentoMaximo(Double comprimentoMaximo) {
		this.comprimentoMaximo = comprimentoMaximo;
	}
	public boolean isEspesura() {
		return espesura;
	}
	public void setEspesura(boolean espesura) {
		this.espesura = espesura;
	}
	public boolean isLongitudinal() {
		return longitudinal;
	}
	public void setLongitudinal(boolean longitudinal) {
		this.longitudinal = longitudinal;
	}
	public boolean isTransversal() {
		return transversal;
	}
	public void setTransversal(boolean transversal) {
		this.transversal = transversal;
	}
	public boolean isTresCharts(){
		return StringUtils.isNotBlank(svgEspessura) && StringUtils.isNotBlank(svgLongitudinal)  && StringUtils.isNotBlank(svgTransversal) ;
	}
	public boolean isDoisCharts(){
		boolean retorno = false;
		if ( StringUtils.isBlank(svgEspessura) || StringUtils.isBlank(svgLongitudinal)  || StringUtils.isBlank(svgTransversal)){
			if (StringUtils.isBlank(svgEspessura) && (StringUtils.isNotBlank(svgLongitudinal)  || StringUtils.isNotBlank(svgTransversal))){
				 retorno = true;
			} else if (StringUtils.isBlank(svgLongitudinal) && (StringUtils.isNotBlank(svgEspessura)  || StringUtils.isNotBlank(svgTransversal))){
					 retorno = true;
		    } else if (StringUtils.isBlank(svgTransversal) && (StringUtils.isNotBlank(svgEspessura)  || StringUtils.isNotBlank(svgLongitudinal))){
					 retorno = true;
			}
		}
		return retorno;
	}
	public boolean isUmCharts(){

		boolean retorno = false; 
		if (StringUtils.isNotBlank(svgEspessura) && (StringUtils.isBlank(svgLongitudinal)  || StringUtils.isBlank(svgTransversal))){
			 	 retorno = true;
		} else if (StringUtils.isNotBlank(svgLongitudinal) && (StringUtils.isBlank(svgEspessura)  || StringUtils.isBlank(svgTransversal))){
				 retorno = true;
	    } else if (StringUtils.isNotBlank(svgTransversal) && (StringUtils.isBlank(svgEspessura)  || StringUtils.isBlank(svgLongitudinal))){
				 retorno = true;
		}
		 
		return retorno;
	}
	public String getUrlLogo() {
		return urlLogo;
	}
	public void setUrlLogo(String urlLogo) {
		this.urlLogo = urlLogo;
	}
}
