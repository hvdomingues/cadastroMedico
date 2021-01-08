package com.hvdomingues.cadastroMedico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hvdomingues.cadastroMedico.dao.jpa.MedicoRepository;
import com.hvdomingues.cadastroMedico.domain.Medico;

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
				medicoNovo.setCelular(medico.getCelular());
			} else if (medico.getEndereco() != null) {
				medicoNovo.setEndereco(medico.getEndereco());
			} else if (medico.getCRM() != null) {
				medicoNovo.setCRM(medico.getCRM());
			} else if (medico.getNomeCompleto() != null) {
				medicoNovo.setNomeCompleto(medico.getNomeCompleto());
			} else if (medico.getTelefone() != null) {
				medicoNovo.setTelefone(medico.getTelefone());
			}

			return medicoRepository.save(medicoNovo);

		}

		return null;
	}

	public Boolean deleteMedicoById(Long id) {

		Medico medico = getMedicoById(id).get();
		medico.setIsDeleted(true);
		medico = updateMedico(medico);
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
		
		for (Medico medico : foundMedicos) {
			if(medico.getIsDeleted()) {
				foundMedicos.remove(medico);
			}
		}
		
		return foundMedicos;
	}

}
