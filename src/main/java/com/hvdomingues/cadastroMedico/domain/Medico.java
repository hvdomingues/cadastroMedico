package com.hvdomingues.cadastroMedico.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "medico")
public class Medico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "crm", unique = true)
	private Integer crm;

	@Column(name = "nome_completo", nullable = false)
	private String nomeCompleto;

	@Column(name = "telefone", nullable = false)
	private Integer telefone;
	@Column(name = "celular", nullable = false)
	private Integer celular;
	@Column(name = "cep", nullable = false)
	private Integer cep;

	public Medico() {

	}

	public Medico(String nomeCompleto, Integer crm, Integer telefone, Integer celular, Integer cep) {

		this.nomeCompleto = nomeCompleto;
		this.crm = crm;
		this.telefone = telefone;
		this.celular = celular;
		this.cep = cep;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Integer getCRM() {
		return crm;
	}

	public void setCRM(Integer cRM) {
		crm = cRM;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public Integer getCelular() {
		return celular;
	}

	public void setCelular(Integer celular) {
		this.celular = celular;
	}

	public Integer getCEP() {
		return cep;
	}

	public void setCEP(Integer cEP) {
		cep = cEP;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((crm == null) ? 0 : crm.hashCode());
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
		if (crm == null) {
			if (other.crm != null)
				return false;
		} else if (!crm.equals(other.crm))
			return false;
		return true;
	}

}
