package com.hvdomingues.cadastroMedico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.EnderecoRepository;
import com.hvdomingues.cadastroMedico.domain.Endereco;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.exception.NotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	@Autowired
	private MedicoService medicoService;

	public Endereco createEndereco(Medico medico, Endereco endereco) {

		if (medicoService.getMedicoById(medico.getId()).get() != medico) {
			throw new NotFoundException("Médico requerido não ativo");
		}

		endereco.setMedico(medico);

		return enderecoRepository.save(endereco);

	}

	public List<Endereco> getEnderecoByCep(String cep) {

		List<Endereco> foundEnderecos = enderecoRepository.findByCep(cep);

		if (foundEnderecos.size() == 0) {
			throw new NotFoundException("Nenhum endereço encontrado pelo CEP digitado");
		} else {
			return foundEnderecos;
		}

	}

	public List<Endereco> getEnderecoByEstado(String estado) {

		List<Endereco> foundEnderecos = enderecoRepository.findByEstadoStartingWithIgnoreCase(estado);

		if (foundEnderecos.size() == 0) {
			throw new NotFoundException("Nenhum endereço encontrado pelo Estado digitado");
		} else {
			return foundEnderecos;
		}
	}

	public List<Endereco> getEnderecoByCidade(String cidade) {

		List<Endereco> foundEnderecos = enderecoRepository.findByCidadeStartingWithIgnoreCase(cidade);

		if (foundEnderecos.size() == 0) {
			throw new NotFoundException("Nenhum endereço encontrado pela Cidade digitado");
		} else {
			return foundEnderecos;
		}
	}

	public List<Endereco> getEnderecoByBairro(String bairro) {

		List<Endereco> foundEnderecos = enderecoRepository.findByBairroStartingWithIgnoreCase(bairro);

		if (foundEnderecos.size() == 0) {
			throw new NotFoundException("Nenhum endereço encontrado pelo Bairro digitado");
		} else {
			return foundEnderecos;
		}
	}

	
	public List<Endereco> getEnderecoByRua(String rua) {

		List<Endereco> foundEnderecos = enderecoRepository.findByRuaStartingWithIgnoreCase(rua);

		if (foundEnderecos.size() == 0) {
			throw new NotFoundException("Nenhum endereço encontrado pela rua digitada");
		} else {
			return foundEnderecos;
		}
	}
}
