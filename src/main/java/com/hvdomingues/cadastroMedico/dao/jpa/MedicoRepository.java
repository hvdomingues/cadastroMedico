package com.hvdomingues.cadastroMedico.dao.jpa;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.Medico;

public interface MedicoRepository extends PagingAndSortingRepository<Medico, Long> {
	
	Page<Medico> findByIsDeleted(Boolean isDeleted, Pageable pageable);
	
	Page<Medico> findAll(Example<Medico> medico, Pageable pageable);
	
}
