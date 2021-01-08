package com.hvdomingues.cadastroMedico.dao.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.Medico;

public interface MedicoRepository extends PagingAndSortingRepository<Medico, Long> {
	
	List<Medico> findByCrm(String crm);
	List<Medico> findByTelefone(String telefone);
	List<Medico> findByCelular(String celular);
	
	List<Medico> findByNomeCompletoStartingWithIgnoreCase(String nomeCompleto);
	
	List<Medico> findByIsDeleted(Boolean isDeleted);
	
}
