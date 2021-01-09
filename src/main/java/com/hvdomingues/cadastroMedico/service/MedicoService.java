package com.hvdomingues.cadastroMedico.service;

import java.util.List;

import com.hvdomingues.cadastroMedico.dto.MedicoDto;

public interface MedicoService {

	MedicoDto createMedico(MedicoDto medico);

	List<MedicoDto> getAllMedicos();

	MedicoDto updateMedico(MedicoDto medicoDto);

	Boolean deleteMedicoById(Long id);

	MedicoDto getMedicoById(Long id);

	List<MedicoDto> findBy(MedicoDto medicoDto);

	
	
}
