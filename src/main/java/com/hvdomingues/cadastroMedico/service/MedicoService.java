package com.hvdomingues.cadastroMedico.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.MedicoRepository;
import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.exception.DatabaseException;
import com.hvdomingues.cadastroMedico.exception.IllegalArgumentException;

@Service
public class MedicoService {

	@Autowired
	private MedicoRepository medicoRepository;

	public MedicoService() {
	}

	public Medico createMedico(Medico medico) {
		return medicoRepository.save(medico);
	}

	public Optional<Medico> getMedicoById(Long id) {
		return medicoRepository.findById(id);
	}

	public Medico updateMedico(Medico medico) {

		Optional<Medico> savedMedico = getMedicoById(medico.getId());

		if (savedMedico.isPresent() == true) {

			Medico medicoNovo = savedMedico.get();

			if (medico.getCelular() != null) {
				
				if(validaTelefone(medico.getCelular())) {
					medicoNovo.setCelular(medico.getCelular());
				}else {
					throw new IllegalArgumentException("O celular informado está incorreto, mudança não realizada");
				}
				
			} else if (medico.getEndereco() != null) {
				medicoNovo.setEndereco(medico.getEndereco());
			} else if (medico.getCRM() != null) {
				if(validaCrm(medico.getCRM())) {
					medicoNovo.setCRM(medico.getCRM());
				}else {
					throw new IllegalArgumentException("O CRM informado deve conter apenas números e ter um máximo de 7 caracteres.");
				}	
				
				
			} else if (medico.getNomeCompleto() != null) {
				if(medico.getNomeCompleto().length() > 120) {
					throw new IllegalArgumentException("O nome informado deve conter no máximo 120 caracteres.");
				}else {
					medicoNovo.setNomeCompleto(medico.getNomeCompleto());
				}
				
			} else if (medico.getTelefone() != null) {

				if(validaTelefone(medico.getTelefone())) {
					medicoNovo.setTelefone(medico.getTelefone());
				}else {
					throw new IllegalArgumentException("O telefone informado está incorreto, mudança não realizada");
				}
				
			}

			return medicoRepository.save(medicoNovo);

		}else {
			throw new IllegalArgumentException("O médico com o ID informado não foi encontrado");

		}

	}

	public Boolean deleteMedicoById(Long id) {

		Medico medico = getMedicoById(id).get();
		medico.setIsDeleted(true); 
		medico = medicoRepository.save(medico);
		return medico.getIsDeleted();
	}

	public Iterable<Medico> getAllMedicos() {
		return medicoRepository.findByIsDeleted(false);
	}

	public List<Medico> getMedicosByCelular(String celular) {

		List<Medico> foundMedicos = medicoRepository.findByCelular(celular);
		
		return removeDeleted(foundMedicos);
	}

	public List<Medico> getMedicosByTelefone(String telefone) {
		
		List<Medico> foundMedicos = medicoRepository.findByTelefone(telefone);
		
		return removeDeleted(foundMedicos);
	}


	public List<Medico> getMedicosByCrm(String crm) {
		
		List<Medico> foundMedicos = medicoRepository.findByCrm(crm);
		
		return removeDeleted(foundMedicos);
		
		
	}

	public List<Medico> getMedicosByNomeCompleto(String nomeCompleto) {
		
		List<Medico> foundMedicos = medicoRepository.findByNomeCompletoStartingWithIgnoreCase(nomeCompleto);
		
		return removeDeleted(foundMedicos);
	}
	
	
	public List<Medico> getMedicosByCep(String cep) {
		
		//List<Medico> foundMedicos = medicoRepository.findByCep(cep);
		
		//return removeDeleted(foundMedicos);
		
		return null;
	}
	
	
	private List<Medico> removeDeleted(List<Medico> foundMedicos){
		
		List<Medico> activeMedicos = new ArrayList<>();
		
		if(foundMedicos.size() == 0) {
			throw new DatabaseException("Não foi encontrado nenhum médico com o critério informado.");
		}
		
		for (Medico medico : foundMedicos) {
			if(!medico.getIsDeleted()) {
				activeMedicos.add(medico);
			}
			
		}
		
		if(activeMedicos.size() == 0) {
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
		
		Pattern crmPattern = Pattern.compile("^[0-9]{0,6}+$");
		Matcher matcher = crmPattern.matcher(crm);
		
		boolean matchFound = matcher.find();
		
		return matchFound;
	}

}
