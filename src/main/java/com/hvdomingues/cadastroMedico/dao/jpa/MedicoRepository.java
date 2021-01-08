package com.hvdomingues.cadastroMedico.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.Medico;

public interface MedicoRepository extends PagingAndSortingRepository<Medico, Long> {
	
	
	
}
