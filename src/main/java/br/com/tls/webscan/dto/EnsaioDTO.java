package br.com.tls.webscan.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import br.com.tls.webscan.entity.DadosEnsaioVO;
import br.com.tls.webscan.entity.EnsaioVO;
import br.com.tls.webscan.entity.TipoEnsaioEnum;

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
	private Long margemEspesura;
	private Long margemLongitudinal;
	private Long margemTransversal; 

	private boolean height150;
	private boolean height225;
	private boolean height450;  

	private List<DadosEnsaioDTO> dadosEspessura;
	private List<DadosEnsaioDTO> dadosLongitudinal;
	private List<DadosEnsaioDTO> dadosTransversal;
	
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
			this.margemEspesura = ensaio.getMargemEspesura();
			this.margemLongitudinal = ensaio.getMargemLongitudinal();
			this.margemTransversal = ensaio.getMargemTransversal();
			if (ensaio.getDadosEnsaio() != null && !ensaio.getDadosEnsaio().isEmpty()){
				this.dadosEspessura  = new ArrayList<DadosEnsaioDTO>();
				this.dadosLongitudinal = new ArrayList<DadosEnsaioDTO>();
				this.dadosTransversal  = new ArrayList<DadosEnsaioDTO>();
				for (DadosEnsaioVO dado :ensaio.getDadosEnsaio() ){
					DadosEnsaioDTO dadoDto = new DadosEnsaioDTO(dado);
					if (TipoEnsaioEnum.ESPESSURA.equals(dado.getTipo())){
						this.dadosEspessura.add(dadoDto);						
					} else if (TipoEnsaioEnum.TRANSVERSAL.equals(dado.getTipo())){
						this.dadosTransversal.add(dadoDto);
					}else if (TipoEnsaioEnum.LONGITUDINAL.equals(dado.getTipo())){
						this.dadosLongitudinal.add(dadoDto);						
					}
				}
			}
				
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
		ensaio.setMargemEspesura(margemEspesura);
		ensaio.setMargemLongitudinal(margemLongitudinal);
		ensaio.setMargemTransversal(margemTransversal);
		ensaio.setDadosEnsaio(new ArrayList<DadosEnsaioVO>());
		if (this.dadosEspessura !=null && !this.dadosEspessura.isEmpty()){ 
			for (DadosEnsaioDTO dadosDto : this.dadosEspessura){
				ensaio.getDadosEnsaio().add(dadosDto.toDadosEnsaio());
			}
		}
		if (this.dadosLongitudinal !=null && !this.dadosLongitudinal.isEmpty()){ 
			for (DadosEnsaioDTO dadosDto : this.dadosLongitudinal){
				ensaio.getDadosEnsaio().add(dadosDto.toDadosEnsaio());
			}
		}
		if (this.dadosTransversal !=null && !this.dadosTransversal.isEmpty()){ 
			for (DadosEnsaioDTO dadosDto : this.dadosTransversal){
				ensaio.getDadosEnsaio().add(dadosDto.toDadosEnsaio());
			}
		}
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

public List<DadosEnsaioDTO> getDadosEspessura() {
	if (dadosEspessura == null){
		dadosEspessura = new ArrayList<DadosEnsaioDTO>();
	}
	return dadosEspessura;
}

public void setDadosEspessura(List<DadosEnsaioDTO> dadosEspessura) {
	this.dadosEspessura = dadosEspessura;
}

public List<DadosEnsaioDTO> getDadosLongitudinal() {
	if (dadosLongitudinal == null){
		dadosLongitudinal = new ArrayList<DadosEnsaioDTO>();
	}
	return dadosLongitudinal;
}

public void setDadosLongitudinal(List<DadosEnsaioDTO> dadosLongitudinal) {
	this.dadosLongitudinal = dadosLongitudinal;
}

public List<DadosEnsaioDTO> getDadosTransversal() {
	if (dadosTransversal == null){
		dadosTransversal = new ArrayList<DadosEnsaioDTO>();
	}
	return dadosTransversal;
}

public void setDadosTransversal(List<DadosEnsaioDTO> dadosTransversal) {
	this.dadosTransversal = dadosTransversal;
}

 
 
	
	
} 
