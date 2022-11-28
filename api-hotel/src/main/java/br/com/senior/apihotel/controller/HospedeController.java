package br.com.senior.apihotel.controller;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
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

import br.com.senior.apihotel.controller.form.AtualizacaoHospedeForm;
import br.com.senior.apihotel.dto.HospedeDto;
import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.HospedeRepository;
import br.com.senior.apihotel.service.HospedeService;

@RestController
@RequestMapping("/api/hospedes")
public class HospedeController {

	@Autowired
	private HospedeRepository hospedeRepository;

	@Autowired
	private HospedeService hospedeService;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Hospede criar(@Valid @RequestBody Hospede hospede) {

		// return hospedeRepository.save(hospede);
		return hospedeService.salvar(hospede);
	}

	@GetMapping
	public List<HospedeDto> listarHospedes() {

		List<Hospede> hospedes = hospedeRepository.findAll();
		return HospedeDto.converter(hospedes);

	}

	@GetMapping("/{idHospede}")
	public ResponseEntity<HospedeDto> buscarHospede(@PathVariable Long idHospede) {

		Optional<Hospede> optional = hospedeRepository.findById(idHospede);
		if (optional.isPresent()) {

			Hospede hospede = hospedeRepository.findById(idHospede).get();
			ModelMapper modelMapper = new ModelMapper();
			HospedeDto dto = modelMapper.map(hospede, HospedeDto.class);

			return ResponseEntity.ok().body(dto);
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{hospedeId}")
	@Transactional // Dispara o commit no db
	public ResponseEntity<HospedeDto> atualizar(@PathVariable Long hospedeId,
			@Valid @RequestBody AtualizacaoHospedeForm form) {

		Optional<Hospede> optional = hospedeRepository.findById(hospedeId);
		if (optional.isPresent()) {
			Hospede hospede = form.atualizar(hospedeId, hospedeRepository);

			return ResponseEntity.ok(new HospedeDto(hospede));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{hospedeId}")
	public ResponseEntity<Void> remover(@PathVariable Long hospedeId) {
		if (!hospedeRepository.existsById(hospedeId)) {
			return ResponseEntity.notFound().build();
		}
		hospedeService.excluir(hospedeId);
		return ResponseEntity.noContent().build();
	}

}
