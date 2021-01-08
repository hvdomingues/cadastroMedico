package com.hvdomingues.cadastroMedico.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.hvdomingues.cadastroMedico.domain.pk.MedicoEspecialidadePK;

@Entity
@Table(name = "medico_especialidade")
public class MedicoEspecialidade implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private MedicoEspecialidadePK id = new MedicoEspecialidadePK();
	
	public MedicoEspecialidade() {
	}
	
	public MedicoEspecialidade(Medico medico, Especialidade especialidade) {
		id.setMedico(medico);
		id.setEspecialidade(especialidade);
	}
	
	public Medico getMedico() {
		return id.getMedico();
	}
	
	public void setMedico(Medico medico) {
		id.setMedico(medico);
	}
	
	public Especialidade getEspecialidade() {
		return id.getEspecialidade();
	}
	
	public void setEspecialidade(Especialidade especialidade) {
		id.setEspecialidade(especialidade);
	}
	
	
	
	
}
