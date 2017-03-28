package br.com.tls.webscan.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name="ensaio")
public class EnsaioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column
	private String ordemDeServico;

	@Temporal(TemporalType.DATE)
	private Date dataDoEnsaio;

	@Column
	private String item;

	@Column
	private String codigo;

	@Column
	private String clienteEmpresa;

	@Column
	private Double avanco;

	@Column
	private Double comprimentoMaximo;
	

	@Column 
	private Long margemEspesura;
	
	@Column
	private Boolean espesura;
	
	@Column
	private Boolean longitudinal;
	
	@Column 
	private Long margemLongitudinal;
	
	@Column
	private Boolean transversal;
	
	@Column 
	private Long margemTransversal; 

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ensaio", fetch = FetchType.LAZY)
	@OrderBy("tipo, valorX")
    private List<DadosEnsaioVO> dadosEnsaio; 
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public Boolean getEspesura() {
		return espesura;
	}
	public void setEspesura(Boolean espesura) {
		this.espesura = espesura;
	}
	public Boolean getLongitudinal() {
		return longitudinal;
	}
	public void setLongitudinal(Boolean longitudinal) {
		this.longitudinal = longitudinal;
	}
	public Boolean getTransversal() {
		return transversal;
	}
	public void setTransversal(Boolean transversal) {
		this.transversal = transversal;
	}
	public Long getMargemEspesura() {
		return margemEspesura;
	}
	public void setMargemEspesura(Long margemEspesura) {
		this.margemEspesura = margemEspesura;
	}
	public Long getMargemLongitudinal() {
		return margemLongitudinal;
	}
	public void setMargemLongitudinal(Long margemLongitudinal) {
		this.margemLongitudinal = margemLongitudinal;
	}
	public Long getMargemTransversal() {
		return margemTransversal;
	}
	public void setMargemTransversal(Long margemTransversal) {
		this.margemTransversal = margemTransversal;
	}
	public List<DadosEnsaioVO> getDadosEnsaio() {
 
		return dadosEnsaio;
	}
	public void setDadosEnsaio(List<DadosEnsaioVO> dadosEnsaio) {
		this.dadosEnsaio = dadosEnsaio;
	}
  
}
