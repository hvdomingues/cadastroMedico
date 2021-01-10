package com.hvdomingues.cadastroMedico.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "medico_id", unique = true)
	private Long id;

	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@Column(name = "telefone", nullable = false)
	private String telefone;
	
	@Column(name = "celular", nullable = false)
	private String celular;

	@Column(name = "crm", nullable = false)
	private String crm;

	@Column(name = "is_deleted")
	private Boolean isDeleted = false;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name = "medico_id")
	private Endereco endereco;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="medico")
	private List<MedicoEspecialidade> especialidades;
	

	public Medico() {

	}

	public Medico(String nomeCompleto, String crm, String telefone, String celular, Endereco endereco) {

		this.nomeCompleto = nomeCompleto;
		this.crm = crm;
		this.telefone = telefone;
		this.celular = celular;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	

	public List<MedicoEspecialidade> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<MedicoEspecialidade> especialidades) {
		this.especialidades = especialidades;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Medico other = (Medico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	

}
