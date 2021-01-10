package com.hvdomingues.cadastroMedico.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.MedicoEspecialidade;

public interface MedicoEspecialidadeRepository extends PagingAndSortingRepository<MedicoEspecialidade, Long> {

}
