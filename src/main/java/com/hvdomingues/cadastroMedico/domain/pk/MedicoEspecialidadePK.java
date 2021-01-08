package com.hvdomingues.cadastroMedico.domain.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.hvdomingues.cadastroMedico.domain.Especialidade;
import com.hvdomingues.cadastroMedico.domain.Medico;

@Embeddable
public class MedicoEspecialidadePK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name = "medico_id")
	private Medico medico;
	
	@ManyToOne
	@JoinColumn(name = "especialidade_id")
	private Especialidade especialidade;

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}
	
	
	
	
	
	
}
