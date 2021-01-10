package com.hvdomingues.cadastroMedico.dao.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.Endereco;

public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, Long>{

}
