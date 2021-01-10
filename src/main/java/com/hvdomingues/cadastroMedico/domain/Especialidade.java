package com.hvdomingues.cadastroMedico.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "especialidade")
public class Especialidade implements Serializable{
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "especialidade_id")
	private Long id;
	
	@Column(name = "nome_especialidade")
	private String nomeEspecialidade;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="especialidade")
	private List<MedicoEspecialidade> especialidades;
	
	public Especialidade() {	
	}
	
	public Especialidade(Long id) {
		this.id = id;
	}

	public Especialidade(Long id, String nomeEspecialidade) {
		super();
		this.id = id;
		this.nomeEspecialidade = nomeEspecialidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeEspecialidade() {
		return nomeEspecialidade;
	}

	public void setNomeEspecialidade(String nomeEspecialidade) {
		this.nomeEspecialidade = nomeEspecialidade;
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
		Especialidade other = (Especialidade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Especialidade [id=" + id + ", nomeEspecialidade=" + nomeEspecialidade + "]";
	}
	
	
	
	
}
