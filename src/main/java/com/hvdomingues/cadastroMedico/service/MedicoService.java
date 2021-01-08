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
		
		
		if(savedMedico.isPresent() == true) {
			
			Medico medicoNovo = savedMedico.get();
			
			if(medico.getCelular() != null) {
				medicoNovo.setCelular(medico.getCelular());
			}
			else if(medico.getCEP() != null) {
				medicoNovo.setCEP(medico.getCEP());
			}
			else if(medico.getCRM() != null) {
				medicoNovo.setCRM(medico.getCRM());
			}
			else if(medico.getNomeCompleto() != null) {
				medicoNovo.setNomeCompleto(medico.getNomeCompleto());
			}
			else if(medico.getTelefone() != null) {
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
	
	public List<Medico> getMedicos(Medico medico){
		
		if(medico.getCelular() != null) {
			
			return medicoRepository.findByCelular(medico.getCelular());
			
		}
		else if(medico.getCEP() != null) {
			
			return medicoRepository.findByCep(medico.getCEP());
			
		}
		else if(medico.getCRM() != null) {
			
			return medicoRepository.findByCrm(medico.getCRM());
		}
		else if(medico.getNomeCompleto() != null) {
			
			return medicoRepository.findByNomeCompletoIgnoreCase(medico.getNomeCompleto());
		}
		else if(medico.getTelefone() != null) {
			return medicoRepository.findByTelefone(medico.getNomeCompleto());
		}
		
		return null;
		
		
		
	}
}
