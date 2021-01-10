package com.hvdomingues.cadastroMedico.service;

import com.hvdomingues.cadastroMedico.domain.Endereco;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.dto.EnderecoDto;

public interface EnderecoService {

	EnderecoDto attachMedico (Medico medico, Endereco enderecoD);
	
	EnderecoDto entityToDto(Endereco endereco);
	
	Endereco dtoToEntity(EnderecoDto enderecoDto);
	
	Endereco updateEndereco (Endereco endereco, EnderecoDto changesEndereco);

	Endereco saveEndereco(Endereco enderecoToSave);
	
	
}
