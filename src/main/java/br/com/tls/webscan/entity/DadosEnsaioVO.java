package br.com.tls.webscan.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="dadosensaio")
public class DadosEnsaioVO {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
    @ManyToOne(fetch=FetchType.LAZY) 
    @JoinColumn(name="ensaio_id", nullable=false)
	private EnsaioVO ensaio;
    @Enumerated(EnumType.STRING)
	private TipoEnsaioEnum tipo;
    @Column
    private Integer valorX;
	@Column
	private Integer valorY;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public EnsaioVO getEnsaio() {
		return ensaio;
	}
	public void setEnsaio(EnsaioVO ensaio) {
		this.ensaio = ensaio;
	}
	public TipoEnsaioEnum getTipo() {
		return tipo;
	}
	public void setTipo(TipoEnsaioEnum tipo) {
		this.tipo = tipo;
	}
	public Integer getValorX() {
		return valorX;
	}
	public void setValorX(Integer valorX) {
		this.valorX = valorX;
	}
	public Integer getValorY() {
		return valorY;
	}
	public void setValorY(Integer valorY) {
		this.valorY = valorY;
	}
	@Override
	public int hashCode() {

        return (getId() == null) ? System.identityHashCode(this) : getId().hashCode();
	}
	@Override
	public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (this.getClass() != obj.getClass())
            return false;
        if (org.hibernate.Hibernate.getClass(this) != org.hibernate.Hibernate.getClass(obj))
            return false;
        DadosEnsaioVO other = (DadosEnsaioVO) obj;
        if (getId() == null || other.getId() == null)
            return false;
        return getId().equals(other.getId());
	}
		
}
