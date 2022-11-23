package br.com.senior.apihotel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.HospedeRepository;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

	@Autowired
	private HospedeRepository hospedeRepository;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Hospede criar(@RequestBody Hospede hospede) {

		return hospedeRepository.save(hospede);
	}
}
