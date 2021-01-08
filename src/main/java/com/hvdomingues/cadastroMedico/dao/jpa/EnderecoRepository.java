package com.hvdomingues.cadastroMedico.dao.jpa;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.hvdomingues.cadastroMedico.domain.Endereco;

public interface EnderecoRepository extends PagingAndSortingRepository<Endereco, Long>{

	List<Endereco> findByCep(String cep);

	List<Endereco> findByEstadoStartingWithIgnoreCase(String estado);

	List<Endereco> findByCidadeStartingWithIgnoreCase(String cidade);

	List<Endereco> findByBairroStartingWithIgnoreCase(String bairro);

	List<Endereco> findByRuaStartingWithIgnoreCase(String rua);
	
}
