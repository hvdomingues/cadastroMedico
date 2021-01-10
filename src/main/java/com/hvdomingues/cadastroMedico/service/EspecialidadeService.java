package com.hvdomingues.cadastroMedico.service;

import java.util.List;

import com.hvdomingues.cadastroMedico.domain.Especialidade;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.domain.MedicoEspecialidade;
import com.hvdomingues.cadastroMedico.dto.EspecialidadeDto;


public interface EspecialidadeService {

	List<EspecialidadeDto> entityToDto(List<MedicoEspecialidade> especialidades);

	List<MedicoEspecialidade> saveMedicoEspecialidade(List<EspecialidadeDto> especialidades, Medico medico);
	
	Especialidade findById(Long id);
	

}
