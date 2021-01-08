package com.hvdomingues.cadastroMedico.api.rest;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hvdomingues.cadastroMedico.domain.Medico;
import com.hvdomingues.cadastroMedico.service.MedicoService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

	@Autowired
	private MedicoService service;

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public void createHotel(@RequestBody Medico medico, HttpServletRequest request, HttpServletResponse response) {
		Medico createdMedico = this.service.createMedico(medico);
		response.setHeader("Location", request.getRequestURL().append("/").append(createdMedico.getId()).toString());
	}

	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Iterable<Medico>> getAllMedico() {
		
		Iterable<Medico> medicos = service.getAllMedicos();
		
		return ResponseEntity.ok().body(medicos);

	}
	
	

}
