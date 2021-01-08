package com.hvdomingues.cadastroMedico.service;

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
	
	public Optional<Medico> getMedico(Long id) {
		return medicoRepository.findById(id);
	}
	
	public Medico updateMedico(Medico medico) {
		return medicoRepository.save(medico);
	}
	
	public Boolean deleteMedico(Medico medico) {
		medico.setIsDeleted(true);
		medico = updateMedico(medico);
		return medico.getIsDeleted();
	}
	
	public Iterable<Medico> getAllMedicos() {
		return medicoRepository.findAll();
	}
}
