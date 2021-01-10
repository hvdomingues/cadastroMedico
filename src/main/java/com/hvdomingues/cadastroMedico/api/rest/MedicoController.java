package com.hvdomingues.cadastroMedico.api.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
	public ResponseEntity<Page<MedicoDto>> getAllMedicos(@RequestParam( value = "page", required = false, defaultValue = "0") int page,
		    @RequestParam (value = "size", required = false, defaultValue = "10") int size) {

		Page<MedicoDto> medicos = medicoService.getAllMedicos(page, size);

		return new ResponseEntity<Page<MedicoDto>>(medicos, HttpStatus.CREATED);

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
	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteMedico(@RequestParam Long id) {

		Boolean isDeleted = medicoService.deleteMedicoById(id);

		return new ResponseEntity<Boolean>(isDeleted, HttpStatus.OK);

	}

	@GetMapping(value = "/findById")
	public ResponseEntity<MedicoDto> getMedicoById(@RequestParam Long id) {

		MedicoDto resultado = medicoService.getMedicoById(id);

		return ResponseEntity.ok().body(resultado);

	}

	@RequestMapping(value = "/findBy", method = RequestMethod.POST, consumes = { "application/json",
	"application/xml" }, produces = { "application/json", "application/xml" })
	public ResponseEntity<Page<MedicoDto>> getMedicoBy(@RequestBody MedicoDto medicoDto, @RequestParam( value = "page", required = false, defaultValue = "0") int page,
		    @RequestParam (value = "size", required = false, defaultValue = "10") int size)
	{
		
		Page<MedicoDto> medicos = medicoService.findBy(medicoDto, page, size);
		
		
		return new ResponseEntity<Page<MedicoDto>>(medicos, HttpStatus.FOUND);
		
		
	}
	
	
	

	
}
