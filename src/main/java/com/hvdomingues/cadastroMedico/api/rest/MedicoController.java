package com.hvdomingues.cadastroMedico.api.rest;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.dto.MedicoDto;
import com.hvdomingues.cadastroMedico.service.MedicoService;

@RestController
@RequestMapping(value = "/api/medico")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	// Insert method
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<MedicoDto> createMedico(@RequestBody MedicoDto medico) {
		
		MedicoDto createdMedico = this.medicoService.createMedico(medico);
		
		return new ResponseEntity<MedicoDto>(createdMedico, HttpStatus.CREATED);
	}

	// Select all method
	@GetMapping
	public ResponseEntity<List<MedicoDto>> getAllMedicos() {

		List<MedicoDto> medicos = medicoService.getAllMedicos();

		return new ResponseEntity<List<MedicoDto>>(medicos, HttpStatus.CREATED);

	}

	// Update method
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<MedicoDto> updateMedico(@RequestBody MedicoDto medicoDto, HttpServletRequest request,
			HttpServletResponse response) {

		MedicoDto updatedMedico = medicoService.updateMedico(medicoDto);

		return new ResponseEntity<MedicoDto>(updatedMedico, HttpStatus.OK);

	}

	// Soft delete method
	@RequestMapping(value = "", method = RequestMethod.DELETE, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> deleteMedico(@RequestBody Medico medico, HttpServletRequest request,
			HttpServletResponse response) {

		Boolean isDeleted = medicoService.deleteMedicoById(medico.getId());

		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);

	}

	@GetMapping(value = "/findById")
	public ResponseEntity<MedicoDto> getMedicoById(@RequestParam Long id) {

		MedicoDto resultado = medicoService.getMedicoById(id);

		return ResponseEntity.ok().body(resultado);

	}
	
	@RequestMapping(value = "/findBy", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<List<MedicoDto>> getMedicoBy(@RequestBody MedicoDto medicoDto)
	{
		
		List<MedicoDto> medicos = medicoService.findBy(medicoDto);
		
		
		return new ResponseEntity<List<MedicoDto>>(medicos, HttpStatus.FOUND);
		
		
	}
	
	
	

	
}
