package com.hvdomingues.cadastroMedico.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.EnderecoRepository;
import com.hvdomingues.cadastroMedico.domain.Endereco;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.dto.EnderecoDto;
import com.hvdomingues.cadastroMedico.exception.IllegalArgumentException;
import com.hvdomingues.cadastroMedico.exception.NotFoundException;

@Service
public class EnderecoServiceImpl implements EnderecoService{

	@Autowired
	private EnderecoRepository enderecoRepository;
	

	
	

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

	@Override
	public EnderecoDto entityToDto(Endereco endereco) {

		EnderecoDto enderecoDto = new EnderecoDto();
		
		enderecoDto.setBairro(endereco.getBairro());
		enderecoDto.setCep(endereco.getCep());
		enderecoDto.setCidade(endereco.getCidade());
		enderecoDto.setEstado(endereco.getEstado());
		enderecoDto.setId(endereco.getId());
		enderecoDto.setNumero(endereco.getNumero());
		enderecoDto.setRua(endereco.getRua());
		
		return enderecoDto;
	}

	@Override
	public Endereco dtoToEntity(EnderecoDto enderecoDto) {
		
		Endereco novoEndereco = new Endereco();
		
		
		if(enderecoDto.getCep() != null && enderecoDto.getCep().length() == 8) {
			
			novoEndereco.setCep(enderecoDto.getCep());
			
			if(enderecoDto.getEstado() != null && enderecoDto.getEstado().length() == 2) {
				
				novoEndereco.setEstado(enderecoDto.getEstado());
				
				if(enderecoDto.getCidade() != null) {
					
					novoEndereco.setCidade(enderecoDto.getCidade());
					
					if(enderecoDto.getBairro() != null) {
						
						novoEndereco.setBairro(enderecoDto.getBairro());
						
						if(enderecoDto.getRua() != null) {
							
							novoEndereco.setRua(enderecoDto.getRua());
							
							if(enderecoDto.getNumero() != null) {
								
								novoEndereco.setNumero(enderecoDto.getNumero());
								
							}else {
								throw new IllegalArgumentException("O número deve estar preenchido.");
							}
							
						}else {
							throw new IllegalArgumentException("A rua deve estar preenchida.");
						}
						
					}else {
						throw new IllegalArgumentException("O Bairro deve estar preenchido.");
					}
					
				}else {
					throw new IllegalArgumentException("A cidade deve estar preenchida.");
				}
				
			}else {
				throw new IllegalArgumentException("O Estado deve estar preenchido e com o máximo de dois caracteres");
			}
			
			
		}else {
			throw new IllegalArgumentException("O CEP deve ter exatos oito digítos númericos apenas.");
		}
		
		enderecoRepository.save(novoEndereco);
		
		return novoEndereco;
		

	}

	@Override
	public EnderecoDto attachMedico(Medico medico, Endereco endereco) {
		
		endereco.setMedico(medico);
		
		endereco = enderecoRepository.save(endereco);
		
		return entityToDto(endereco);
		
		
	}
	
	
	
}
