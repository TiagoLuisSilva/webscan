package br.com.tls.webscan.uteis;

 
import java.util.ArrayList;
import java.util.List;

public class ParametrosRelVO {
	private List objetos;
	private String nomeRelatorio;
	private String diretorioRel;
	public List getObjetos() {
		if (objetos == null){
			objetos = new ArrayList<>();
		}
		return objetos;
	}
	public void setObjetos(List objetos) {
		this.objetos = objetos;
	}
	public String getNomeRelatorio() {
		return nomeRelatorio;
	}
	public void setNomeRelatorio(String nomeRelatorio) {
		this.nomeRelatorio = nomeRelatorio;
	}
	public String getDiretorioRel() {
		return diretorioRel;
	}
	public void setDiretorioRel(String diretorioRel) {
		this.diretorioRel = diretorioRel;
	}
	
	
}
