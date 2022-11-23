package br.com.senior.apihotel.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.HospedeRepository;
import br.com.senior.apihotel.service.HospedeService;

@RestController
@RequestMapping("/api/hospedes")
@Transactional
public class HospedeController {

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private HospedeService hospedeService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Hospede criar(@RequestBody Hospede hospede) {

		return hospedeRepository.save(hospede);
	}

	@GetMapping
	public List<Hospede> listarHospedes() {
		return hospedeRepository.findAll();
	}

	@GetMapping("/{idHospede}")
	public Hospede findById(@PathVariable Long idHospede) {

		
		
		//Hospede hospede = hospedeRepository.findById(idHospede).get();
		
		return hospedeService.buscar(idHospede);
		
		 
	}
	
	
	

	@PutMapping("/{hospedeId}")
	public ResponseEntity<Hospede> atualizar(@Valid @PathVariable Long hospedeId, @RequestBody Hospede hospede) {

		if (!hospedeRepository.existsById(hospedeId)) {

			return ResponseEntity.notFound().build();
		}
		hospede.setId(hospedeId);
		// cliente = clienteRepository.save(cliente);
		hospedeService.salvar(hospede);

		return ResponseEntity.ok(hospede);

	}

	@DeleteMapping("/{hospedeId}")
	public ResponseEntity<Void> remover(@PathVariable Long hospedeId) {
		if (!hospedeRepository.existsById(hospedeId)) {
			return ResponseEntity.notFound().build();
		}
		// clienteRepository.deleteById(clienteId);
		hospedeService.excluir(hospedeId);
		return ResponseEntity.noContent().build();
	}

}
