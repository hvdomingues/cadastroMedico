package com.hvdomingues.cadastroMedico.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.MedicoRepository;
import com.hvdomingues.cadastroMedico.domain.Endereco;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.dto.EnderecoDto;
import com.hvdomingues.cadastroMedico.dto.MedicoDto;
import com.hvdomingues.cadastroMedico.exception.IllegalArgumentException;
import com.hvdomingues.cadastroMedico.exception.NotFoundException;

@Service
public class MedicoServiceImpl implements MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private EspecialidadeService especialidadeService;

	@Autowired
	private EnderecoService enderecoService;

	public MedicoServiceImpl() {
	}

	public MedicoDto createMedico(MedicoDto medicoDto) {

		Medico novoMedico = new Medico();

		novoMedico = dtoToEntity(novoMedico, medicoDto);

		novoMedico.setEndereco(enderecoService.saveEndereco(enderecoService.dtoToEntity(medicoDto.getEndereco())));

		medicoRepository.save(novoMedico);
		
		novoMedico.setEspecialidades(especialidadeService.saveMedicoEspecialidade(medicoDto.getEspecialidades(), novoMedico));
		
		medicoDto.setId(novoMedico.getId());

		medicoDto.getEndereco().setId(novoMedico.getEndereco().getId());
		
		medicoDto.setEspecialidades(especialidadeService.entityToDto(novoMedico.getEspecialidades()));

		enderecoService.attachMedico(novoMedico, novoMedico.getEndereco());

		return medicoDto;

	}

	public MedicoDto getMedicoById(Long id) {

		Medico foundMedico = medicoRepository.findById(id).get();

		if (foundMedico == null || foundMedico.getIsDeleted()) {
			throw new NotFoundException("O médico com ID informado não foi encontrado na base de dados.");
		}

		MedicoDto medicoDto = entityToDto(foundMedico);

		return medicoDto;

	}

	public MedicoDto updateMedico(MedicoDto medicoDto) {

		Medico foundMedico = medicoRepository.findById(medicoDto.getId()).get();
		

		Medico novoMedico = new Medico();

		if (foundMedico != null && !foundMedico.getIsDeleted()) {

			novoMedico = dtoToEntity(foundMedico, medicoDto);

			if (medicoDto.getEndereco() != null) {

				novoMedico.setEndereco(
						enderecoService.updateEndereco(foundMedico.getEndereco(), medicoDto.getEndereco()));

			} else {

				novoMedico.setEndereco(foundMedico.getEndereco());

			}

			return entityToDto(medicoRepository.save(novoMedico));

		} else {

			throw new NotFoundException("O médico com ID informado não foi encontrado na base de dados.");

		}

	}

	public Boolean deleteMedicoById(Long id) {

		Medico foundMedico = medicoRepository.findById(id).get();
		
		if(foundMedico.getIsDeleted()) {
			throw new NotFoundException("O médico com ID informado não foi encontrado na base de dados.");
		}

		foundMedico.setIsDeleted(true);
		foundMedico = medicoRepository.save(foundMedico);

		return foundMedico.getIsDeleted();
	}


	@Override
	public Page<MedicoDto> getAllMedicos(Integer page, Integer size) {

		PageRequest pageRequest = PageRequest.of(
                page,
                size);
		
		Page<Medico> foundMedicos = medicoRepository.findByIsDeleted(false, pageRequest);

		Page<MedicoDto> foundMedicosDto = toPageObjectDto(foundMedicos);

		return foundMedicosDto;

	}

	private Boolean validaTelefone(String telefone) {

		Pattern telPattern = Pattern.compile("^\\(?[1-9]{2}\\)? ?(?:[2-8]|9[1-9])[0-9]{3}\\-?[0-9]{4}$");
		Matcher matcher = telPattern.matcher(telefone);

		boolean matchFound = matcher.find();

		return matchFound;
	}

	private Boolean validaCrm(String crm) {

		if (crm.length() == 7) {
			Pattern crmPattern = Pattern.compile("^[0-9]+$");
			Matcher matcher = crmPattern.matcher(crm);

			boolean matchFound = matcher.find();

			return matchFound;
		} else {
			return false;
		}

	}

	private MedicoDto entityToDto(Medico medicoEntity) {

		MedicoDto medicoDto = new MedicoDto();

		if (medicoEntity != null) {

			if (medicoEntity.getId() != null) {
				medicoDto.setId(medicoEntity.getId());
			}
			if (medicoEntity.getNomeCompleto() != null) {
				medicoDto.setNomeCompleto(medicoEntity.getNomeCompleto());
			}
			if (medicoEntity.getCrm() != null) {
				medicoDto.setCrm(medicoEntity.getCrm());
			}
			if (medicoEntity.getTelefone() != null) {
				medicoDto.setTelefone(medicoEntity.getTelefone());
			}
			if (medicoEntity.getCelular() != null) {
				medicoDto.setCelular(medicoEntity.getCelular());
			}
			if (medicoEntity.getEndereco() != null) {
				medicoDto.setEndereco(enderecoService.entityToDto(medicoEntity.getEndereco()));
			}
			if(medicoEntity.getEspecialidades() != null) {
				medicoDto.setEspecialidades(especialidadeService.entityToDto(medicoEntity.getEspecialidades()));
			}

			return medicoDto;

		}

		return null;

	}

	private Medico dtoToEntity(Medico medicoNovo, MedicoDto medicoDto) {

		if (medicoDto.getCelular() != null) {

			if (validaTelefone(medicoDto.getCelular())) {
				medicoNovo.setCelular(medicoDto.getCelular());
			} else {
				throw new IllegalArgumentException("O celular informado está incorreto");
			}

		}
		if (medicoDto.getCrm() != null) {

			if (validaCrm(medicoDto.getCrm())) {
				medicoNovo.setCrm(medicoDto.getCrm());
			} else {
				throw new IllegalArgumentException(
						"O CRM informado deve conter apenas números e ter exatos 7 caracteres.");
			}

		}
		if (medicoDto.getNomeCompleto() != null) {

			if (medicoDto.getNomeCompleto().length() > 120) {
				throw new IllegalArgumentException("O nome informado deve conter no máximo 120 caracteres.");
			} else {
				medicoNovo.setNomeCompleto(medicoDto.getNomeCompleto());
			}

		}
		if (medicoDto.getTelefone() != null) {

			if (validaTelefone(medicoDto.getTelefone())) {
				medicoNovo.setTelefone(medicoDto.getTelefone());
			} else {
				throw new IllegalArgumentException("O telefone informado está incorreto");
			}

		}

		return medicoNovo;

	}

	@Override
	public Page<MedicoDto> findBy(MedicoDto medicoDto, int page, int size) {
		
		PageRequest pageRequest = PageRequest.of(
                page,
                size);

		Medico medico = new Medico();
		Boolean isFilled = false;
		
		if(medicoDto.getNomeCompleto() != null && !medicoDto.getNomeCompleto().isBlank()) {
			medico.setNomeCompleto(medicoDto.getNomeCompleto());
			isFilled = true;
		}
		
		if(medicoDto.getEndereco() != null) {
			
			Endereco enderecoEntity = new Endereco();
			EnderecoDto enderecoDto = medicoDto.getEndereco();
			
			
			if(enderecoDto.getCep() != null && !enderecoDto.getCep().isBlank()) {
				enderecoEntity.setCep(enderecoDto.getCep());
				isFilled = true;
			}
			
			if(enderecoDto.getEstado() != null && !enderecoDto.getEstado().isBlank()) {
				enderecoEntity.setEstado(enderecoDto.getEstado());
				isFilled = true;
			}
			
			if(enderecoDto.getCidade() != null && !enderecoDto.getCidade().isBlank()) {
				enderecoEntity.setCidade(enderecoDto.getCidade());
				isFilled = true;
			}
			
			if(enderecoDto.getBairro() != null && !enderecoDto.getBairro().isBlank()) {
				enderecoEntity.setBairro(enderecoDto.getBairro());
				isFilled = true;
			}
			if(enderecoDto.getRua() != null && !enderecoDto.getRua().isBlank()) {
				enderecoEntity.setRua(enderecoDto.getRua());
				isFilled = true;
			}
			
			medico.setEndereco(enderecoEntity);
			
			
		}
		
		
		if(medicoDto.getTelefone() != null && !medicoDto.getTelefone().isBlank()) {
			medico.setTelefone(medicoDto.getTelefone());
			isFilled = true;
		}
		
		if(medicoDto.getCelular() != null && !medicoDto.getCelular().isBlank()) {
			medico.setCelular(medicoDto.getCelular());
			isFilled = true;
		}
		
		if(medicoDto.getCrm() != null && !medicoDto.getCrm().isBlank()) {
			medico.setCrm(medicoDto.getCrm());
		}

		
		if(isFilled) {
			
			Page<Medico> foundMedicos = medicoRepository.findAll(Example.of(medico, ExampleMatcher.matchingAll().withStringMatcher(StringMatcher.STARTING)
					.withIgnoreCase()), pageRequest);
			
			Page<MedicoDto> foundMedicosDto = toPageObjectDto(foundMedicos);
			
			
			
			if(foundMedicos.getTotalElements() > 0) {
				return foundMedicosDto;
			}
			else {
				throw new NotFoundException("Nenhum médico encontrado com os critérios dados.");
			}
		}
		else {
			throw new IllegalArgumentException("Algum campo de pesquisa deve ser preenchido");
		}	
		
	}
	
	private Page<MedicoDto> toPageObjectDto(Page<Medico> medicos) {
	    Page<MedicoDto> dtos  = medicos.map(this::entityToDto);
	    return dtos;
	}


}
