package com.hvdomingues.cadastroMedico.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.Especialidade;

public interface EspecialidadeRepository extends PagingAndSortingRepository<Especialidade, Long> {

}
