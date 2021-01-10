package com.hvdomingues.cadastroMedico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.EnderecoRepository;
import com.hvdomingues.cadastroMedico.domain.Endereco;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.dto.EnderecoDto;
import com.hvdomingues.cadastroMedico.exception.IllegalArgumentException;

@Service
public class EnderecoServiceImpl implements EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

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
		enderecoDto.setId(endereco.getId());

		return enderecoDto;
	}
	
	@Override
	public Endereco saveEndereco(Endereco enderecoToSave) {
		return enderecoRepository.save(enderecoToSave);
	}

	@Override
	public Endereco dtoToEntity(EnderecoDto enderecoDto) {

		Endereco novoEndereco = new Endereco();

		if (enderecoDto.getCep() != null && enderecoDto.getCep().length() == 8) {

			novoEndereco.setCep(enderecoDto.getCep());

			if (enderecoDto.getEstado() != null && enderecoDto.getEstado().length() == 2) {

				novoEndereco.setEstado(enderecoDto.getEstado());

				if (enderecoDto.getCidade() != null) {

					novoEndereco.setCidade(enderecoDto.getCidade());

					if (enderecoDto.getBairro() != null) {

						novoEndereco.setBairro(enderecoDto.getBairro());

						if (enderecoDto.getRua() != null) {

							novoEndereco.setRua(enderecoDto.getRua());

							if (enderecoDto.getNumero() != null) {

								novoEndereco.setNumero(enderecoDto.getNumero());

							} else {
								throw new IllegalArgumentException("O número deve estar preenchido.");
							}

						} else {
							throw new IllegalArgumentException("A rua deve estar preenchida.");
						}

					} else {
						throw new IllegalArgumentException("O Bairro deve estar preenchido.");
					}

				} else {
					throw new IllegalArgumentException("A cidade deve estar preenchida.");
				}

			} else {
				throw new IllegalArgumentException("O Estado deve estar preenchido e com o máximo de dois caracteres");
			}

		} else {
			throw new IllegalArgumentException("O CEP deve ter exatos oito digítos númericos apenas.");
		}

		return novoEndereco;

	}

	@Override
	public EnderecoDto attachMedico(Medico medico, Endereco endereco) {

		endereco.setMedico(medico);

		endereco = enderecoRepository.save(endereco);

		return entityToDto(endereco);

	}

	@Override
	public Endereco updateEndereco(Endereco endereco, EnderecoDto changesEndereco) {

		if (isStringNullOrBlank(changesEndereco.getBairro()) || isStringNullOrBlank(changesEndereco.getCep())
				|| isStringNullOrBlank(changesEndereco.getEstado()) || isStringNullOrBlank(changesEndereco.getCidade())
				|| isStringNullOrBlank(changesEndereco.getRua())) {

			if (isStringNullOrBlank(changesEndereco.getNumero())) {
				throw new IllegalArgumentException(
						"Todos os campos do endereço devem ser preenchidos para ser realizado o update no cadastro.");
			} else {
				endereco.setNumero(changesEndereco.getNumero());
				return enderecoRepository.save(endereco);
			}

		}
		
		endereco.setCep(changesEndereco.getCep());
		endereco.setEstado(changesEndereco.getEstado());
		endereco.setCidade(changesEndereco.getCidade());
		endereco.setBairro(changesEndereco.getBairro());
		endereco.setRua(changesEndereco.getRua());
		endereco.setNumero(changesEndereco.getNumero());
		
		return enderecoRepository.save(endereco);
		

	}

	private boolean isStringNullOrBlank(String toVerify) {

		if (toVerify != null && !toVerify.isEmpty()) {
			return false;
		} else {
			return true;
		}

	}

}
