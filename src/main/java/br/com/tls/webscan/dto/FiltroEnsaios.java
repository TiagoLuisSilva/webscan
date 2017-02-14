package br.com.tls.webscan.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class FiltroEnsaios {
	
	private String numeroOS;
	private String cliente;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataInicio;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dataFinal;
	
	
	public String getNumeroOS() {
		return numeroOS;
	}
	public void setNumeroOS(String numeroOS) {
		this.numeroOS = numeroOS;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFinal() {
		return dataFinal;
	}
	public void setDataFinal(Date dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	
}
