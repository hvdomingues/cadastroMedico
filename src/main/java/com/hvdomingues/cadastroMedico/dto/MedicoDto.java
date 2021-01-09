package com.hvdomingues.cadastroMedico.dto;

import java.util.List;


public class MedicoDto {


	private Long id;

	
	private String nomeCompleto;

	
	private String telefone;
	

	private String celular;


	private String crm;


	private Boolean isDeleted = false;
	

	private List<EspecialidadeDto> especialidades;
	
	private EnderecoDto endereco;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
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

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public List<EspecialidadeDto> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<EspecialidadeDto> especialidades) {
		this.especialidades = especialidades;
	}

	public EnderecoDto getEnderecoDto() {
		return endereco;
	}

	public void setEnderecoDto(EnderecoDto endereco) {
		this.endereco = endereco;
	}
	
	

	
	
	
}
