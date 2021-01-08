package com.hvdomingues.cadastroMedico.api.rest;

import java.util.List;
import java.util.Optional;

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
@RequestMapping(value = "/api/medico")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicoController {

	@Autowired
	private MedicoService medicoService;

	// Insert method
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Medico> createMedico(@RequestBody Medico medico, HttpServletRequest request, HttpServletResponse response) {
		
		Medico createdMedico = this.medicoService.createMedico(medico);
		
		return ResponseEntity.ok().body(createdMedico);
	}

	// Select all method
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Iterable<Medico>> getAllMedicos() {

		Iterable<Medico> medicos = medicoService.getAllMedicos();

		return ResponseEntity.ok().body(medicos);

	}

	// Update method
	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Medico> updateMedico(@RequestBody Medico medico, HttpServletRequest request,
			HttpServletResponse response) {

		Medico updatedMedico = medicoService.updateMedico(medico);

		return ResponseEntity.ok().body(updatedMedico);

	}

	// Soft delete method
	@RequestMapping(value = "", method = RequestMethod.DELETE, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Boolean> deleteMedico(@RequestBody Medico medico, HttpServletRequest request,
			HttpServletResponse response) {

		Boolean isDeleted = medicoService.deleteMedicoById(medico.getId());

		return ResponseEntity.ok().body(isDeleted);

	}

	// Select by value methods
	@RequestMapping(value = "/id", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Medico> getMedicoById(@RequestBody Long id, HttpServletRequest request,
			HttpServletResponse response) {

		Optional<Medico> resultado = medicoService.getMedicoById(id);

		return ResponseEntity.ok().body(resultado.get());

	}

	@RequestMapping(value = "/nomecompleto", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByNomeCompleto(@RequestBody String nomeCompleto,
			HttpServletRequest request, HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByNomeCompleto(nomeCompleto);

		return ResponseEntity.ok().body(resultados);

	}

	@RequestMapping(value = "/crm", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByCrm(@RequestBody String crm, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByCrm(crm);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/telefone", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByTelefone(@RequestBody String telefone, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByTelefone(telefone);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/celular", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByCelular(@RequestBody String celular, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByCelular(celular);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/cep", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByCep(@RequestBody String cep, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByCep(cep);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/estado", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByEstado(@RequestBody String estado, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByEstado(estado);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/cidade", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByCidade(@RequestBody String cidade, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByCidade(cidade);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/bairro", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByBairro(@RequestBody String bairro, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByBairro(bairro);

		return ResponseEntity.ok().body(resultados);
	}

	@RequestMapping(value = "/rua", method = RequestMethod.GET, consumes = { "application/json",
			"application/xml" }, produces = { "application/json", "application/xml" })
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Medico>> getMedicoByRua(@RequestBody String rua, HttpServletRequest request,
			HttpServletResponse response) {

		List<Medico> resultados = medicoService.getMedicosByRua(rua);

		return ResponseEntity.ok().body(resultados);
	}

}
