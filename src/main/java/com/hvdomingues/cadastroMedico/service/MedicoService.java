package com.hvdomingues.cadastroMedico.service;

import org.springframework.data.domain.Page;

import com.hvdomingues.cadastroMedico.dto.MedicoDto;

public interface MedicoService {

	MedicoDto createMedico(MedicoDto medico);

	MedicoDto updateMedico(MedicoDto medicoDto);

	Boolean deleteMedicoById(Long id);

	MedicoDto getMedicoById(Long id);

	Page<MedicoDto> findBy(MedicoDto medicoDto, int page, int size);

	Page<MedicoDto> getAllMedicos(Integer page, Integer size);

	
	
}
