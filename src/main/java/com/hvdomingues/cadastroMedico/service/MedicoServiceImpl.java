package com.hvdomingues.cadastroMedico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.EspecialidadeRepository;
import com.hvdomingues.cadastroMedico.dao.jpa.MedicoRepository;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.dto.MedicoDto;
import com.hvdomingues.cadastroMedico.exception.DatabaseException;
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

		medicoRepository.save(novoMedico);

		medicoDto.setId(novoMedico.getId());
		
		enderecoService.attachMedico(novoMedico, novoMedico.getEndereco());

		return medicoDto;

	}

	public MedicoDto getMedicoById(Long id) {

		Medico foundMedico = medicoRepository.findById(id).get();

		MedicoDto medicoDto = entityToDto(foundMedico);

		return medicoDto;

	}

	public MedicoDto updateMedico(MedicoDto medicoDto) {

		Medico foundMedico = medicoRepository.findById(medicoDto.getId()).get();

		Medico novoMedico = new Medico();

		if (foundMedico != null) {

			novoMedico = dtoToEntity(foundMedico, medicoDto);
			

			return entityToDto(medicoRepository.save(novoMedico));

		} else {

			throw new NotFoundException("O médico com ID informado não foi encontrado na base de dados.");

		}

	}

	public Boolean deleteMedicoById(Long id) {

		Medico foundMedico = medicoRepository.findById(id).get();
		;
		foundMedico.setIsDeleted(true);
		foundMedico = medicoRepository.save(foundMedico);

		return foundMedico.getIsDeleted();
	}

	public List<MedicoDto> getAllMedicos() {

		List<Medico> foundMedicos = medicoRepository.findByIsDeleted(false);

		List<MedicoDto> foundDtoMedicos = new ArrayList<MedicoDto>();

		for (Medico medico : foundMedicos) {

			foundDtoMedicos.add(entityToDto(medico));

		}

		return foundDtoMedicos;

	}

	private List<Medico> removeDeleted(List<Medico> foundMedicos) {

		List<Medico> activeMedicos = new ArrayList<>();

		if (foundMedicos.size() == 0) {
			throw new DatabaseException("Não foi encontrado nenhum médico com o critério informado.");
		}

		for (Medico medico : foundMedicos) {
			if (!medico.getIsDeleted()) {
				activeMedicos.add(medico);
			}

		}

		if (activeMedicos.size() == 0) {
			throw new DatabaseException("Não foi encontrado nenhum médico ativo com o critério informado.");
		}

		return activeMedicos;
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
			
			
			/*if (medicoEntity.getEndereco() != null) {

				medicoDto.setCep(medicoEntity.getEndereco().getCep());
				medicoDto.setEstado(medicoEntity.getEndereco().getEstado());
				medicoDto.setCidade(medicoEntity.getEndereco().getCidade());
				medicoDto.setBairro(medicoEntity.getEndereco().getBairro());
				medicoDto.setRua(medicoEntity.getEndereco().getRua());
				medicoDto.setNumero(medicoEntity.getEndereco().getNumero());

			}

			List<EspecialidadeDto> especialidadesDto = new ArrayList<EspecialidadeDto>();

			if (medicoEntity.getEspecialidades().size() > 0) {
				for (MedicoEspecialidade medicoEspecialidade : medicoEntity.getEspecialidades()) {

					EspecialidadeDto especialidadeDto = new EspecialidadeDto();
					especialidadeDto.setId(medicoEspecialidade.getEspecialidade().getId());
					especialidadeDto
							.setNomeEspecialidade(medicoEspecialidade.getEspecialidade().getNomeEspecialidade());

					especialidadesDto.add(especialidadeDto);

				}
			}

			medicoDto.setEspecialidades(especialidadesDto);*/

			return medicoDto;

		}

		return null;

	}

	
	// Todo endereco
	// Todo especialidade
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
		
		if(medicoDto.getEnderecoDto() != null) {
			medicoNovo.setEndereco(enderecoService.dtoToEntity(medicoDto.getEnderecoDto()));
		}
		
		return medicoNovo;

	}

	/*
	 * private Medico dtoToEntity(MedicoDto medicoDto) {
	 * 
	 * if (medicoDto != null) {
	 * 
	 * Medico novoMedico = new Medico();
	 * 
	 * novoMedico.setNomeCompleto(medicoDto.getNomeCompleto());
	 * novoMedico.setCrm(medicoDto.getCrm());
	 * novoMedico.setCelular(medicoDto.getCelular());
	 * novoMedico.setTelefone(medicoDto.getTelefone());
	 * 
	 * Endereco novoEndereco = new Endereco();
	 * 
	 * novoEndereco.setCep(medicoDto.getCep());
	 * novoEndereco.setEstado(medicoDto.getEstado());
	 * novoEndereco.setCidade(medicoDto.getCidade());
	 * novoEndereco.setBairro(medicoDto.getBairro());
	 * novoEndereco.setRua(medicoDto.getRua());
	 * novoEndereco.setNumero(medicoDto.getNumero());
	 * novoEndereco.setMedico(novoMedico);
	 * 
	 * novoMedico.setEndereco(novoEndereco);
	 * 
	 * List<MedicoEspecialidade> especialidades = new
	 * ArrayList<MedicoEspecialidade>();
	 * 
	 * if (medicoDto.getEspecialidades() != null) {
	 * 
	 * for (EspecialidadeDto item : medicoDto.getEspecialidades()) {
	 * 
	 * MedicoEspecialidade medicoEspecialidade = new MedicoEspecialidade();
	 * 
	 * medicoEspecialidade.setMedico(novoMedico);
	 * 
	 * medicoEspecialidade.setEspecialidade(especialidadeRepository.findById(item.
	 * getId()).get());
	 * 
	 * especialidades.add(medicoEspecialidade);
	 * 
	 * } }
	 * 
	 * novoMedico.setEspecialidades(especialidades);
	 * 
	 * if (medicoDto.getId() != null) {
	 * 
	 * novoMedico.setId(medicoDto.getId());
	 * 
	 * }
	 * 
	 * return novoMedico;
	 * 
	 * }
	 * 
	 * return null; }
	 * 
	 * public List<Medico> getMedicosByCelular(String celular) {
	 * 
	 * List<Medico> foundMedicos = medicoRepository.findByCelular(celular);
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByTelefone(String telefone) {
	 * 
	 * List<Medico> foundMedicos = medicoRepository.findByTelefone(telefone);
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByCrm(String crm) {
	 * 
	 * List<Medico> foundMedicos = medicoRepository.findByCrm(crm);
	 * 
	 * return removeDeleted(foundMedicos);
	 * 
	 * }
	 * 
	 * public List<Medico> getMedicosByNomeCompleto(String nomeCompleto) {
	 * 
	 * List<Medico> foundMedicos =
	 * medicoRepository.findByNomeCompletoStartingWithIgnoreCase(nomeCompleto);
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByCep(String cep) {
	 * 
	 * List<Endereco> foundEnderecos = enderecoService.getEnderecoByCep(cep);
	 * 
	 * List<Medico> foundMedicos = new ArrayList<>();
	 * 
	 * for (Endereco endereco : foundEnderecos) {
	 * 
	 * foundMedicos.add(endereco.getMedico());
	 * 
	 * }
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByBairro(String bairro) {
	 * 
	 * List<Endereco> foundEnderecos = enderecoService.getEnderecoByBairro(bairro);
	 * 
	 * List<Medico> foundMedicos = new ArrayList<>();
	 * 
	 * for (Endereco endereco : foundEnderecos) {
	 * 
	 * foundMedicos.add(endereco.getMedico());
	 * 
	 * }
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByEstado(String estado) {
	 * 
	 * List<Endereco> foundEnderecos = enderecoService.getEnderecoByEstado(estado);
	 * 
	 * List<Medico> foundMedicos = new ArrayList<>();
	 * 
	 * for (Endereco endereco : foundEnderecos) {
	 * 
	 * foundMedicos.add(endereco.getMedico());
	 * 
	 * }
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByCidade(String cidade) {
	 * 
	 * List<Endereco> foundEnderecos = enderecoService.getEnderecoByCidade(cidade);
	 * 
	 * List<Medico> foundMedicos = new ArrayList<>();
	 * 
	 * for (Endereco endereco : foundEnderecos) {
	 * 
	 * foundMedicos.add(endereco.getMedico());
	 * 
	 * }
	 * 
	 * return removeDeleted(foundMedicos); }
	 * 
	 * public List<Medico> getMedicosByRua(String rua) {
	 * 
	 * List<Endereco> foundEnderecos = enderecoService.getEnderecoByRua(rua);
	 * 
	 * List<Medico> foundMedicos = new ArrayList<>();
	 * 
	 * for (Endereco endereco : foundEnderecos) {
	 * 
	 * foundMedicos.add(endereco.getMedico());
	 * 
	 * }
	 * 
	 * return removeDeleted(foundMedicos); }
	 */

}
