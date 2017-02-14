package br.com.tls.webscan.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.tls.webscan.entity.EnsaioVO;

public class EnsaioDTO {
 
	private Long id; 

    @NotEmpty(message = "Informe o campo Ordem de Serço.")
	private String ordemDeServico; 
    @NotNull(message = "Informe o campo Data do Ensaio.")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataDoEnsaio; 
    @NotEmpty(message = "Informe o campo Item.")
	private String item; 
    @NotEmpty(message = "Informe o campo Código;.")
	private String codigo; 
    @NotEmpty(message = "Informe o campo Cliente/Empresa.")
	private String clienteEmpresa;  
	private Double avanco; 	
	private Double comprimentoMaximo;  
	private boolean espesura; 
	private boolean longitudinal; 
	private boolean transversal;  

	private boolean height150;
	private boolean height225;
	private boolean height450;  
	
	public EnsaioDTO(){
	}
	
	public EnsaioDTO(EnsaioVO ensaio){
		if  (ensaio != null){
			this.id = ensaio.getId();
			this.ordemDeServico = ensaio.getOrdemDeServico();
			this.dataDoEnsaio = ensaio.getDataDoEnsaio();
			this.item = ensaio.getItem();
			this.codigo = ensaio.getCodigo();
			this.clienteEmpresa = ensaio.getClienteEmpresa();
			this.avanco = ensaio.getAvanco();
			this.comprimentoMaximo = ensaio.getComprimentoMaximo();
			this.espesura = ensaio.getEspesura();
			this.longitudinal = ensaio.getLongitudinal();
			this.transversal = ensaio.getTransversal(); 
			calculaTamanho();
		}
	}

	public EnsaioVO toEnsaioVO(){
		EnsaioVO ensaio = new EnsaioVO();
		ensaio.setId(id);
		ensaio.setOrdemDeServico(ordemDeServico);
		ensaio.setDataDoEnsaio(dataDoEnsaio);
		ensaio.setItem(item);
		ensaio.setClienteEmpresa(clienteEmpresa);
		ensaio.setCodigo(codigo);
		ensaio.setAvanco(avanco);
		ensaio.setComprimentoMaximo(comprimentoMaximo);
		ensaio.setEspesura(espesura);
		ensaio.setLongitudinal(longitudinal);
		ensaio.setTransversal(transversal); 
		
		
		return ensaio;
	}
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

 

	
   public boolean isHeight150() {
		return height150;
	}

	public void setHeight150(boolean height150) {
		this.height150 = height150;
	}

	public boolean isHeight225() {
		return height225;
	}

	public void setHeight225(boolean height225) {
		this.height225 = height225;
	}

	public boolean isHeight450() {
		return height450;
	}

	public void setHeight450(boolean height450) {
		this.height450 = height450;
	}

	public void setEspesura(boolean espesura) {
		this.espesura = espesura;
	}

	public void setLongitudinal(boolean longitudinal) {
		this.longitudinal = longitudinal;
	}

	public void setTransversal(boolean transversal) {
		this.transversal = transversal;
	}

private void calculaTamanho(){
		if ((espesura && longitudinal && !transversal) || 
			(espesura && transversal  && !longitudinal)  || 
			(longitudinal && transversal  && !espesura) ){
			setHeight150(false);
			setHeight225(true);
			setHeight450(false);
		} else if (espesura && longitudinal &&  transversal){
			setHeight150(true);
			setHeight225(false);
			setHeight450(false);
		} else if ((espesura && !longitudinal &&  !transversal) || 
				(!espesura &&  longitudinal &&  !transversal) || 
				(!espesura && !longitudinal &&   transversal)){
			setHeight150(false);
			setHeight225(false);
			setHeight450(true);
		}
	}

 
	
	
} 
