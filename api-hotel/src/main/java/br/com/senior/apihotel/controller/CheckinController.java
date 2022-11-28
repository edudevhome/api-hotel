package br.com.senior.apihotel.controller;

import java.net.URI;
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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senior.apihotel.controller.form.CheckinForm;
import br.com.senior.apihotel.dto.CheckinDto;
import br.com.senior.apihotel.model.Checkin;
import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.CheckinRepository;
import br.com.senior.apihotel.repository.HospedeRepository;
import br.com.senior.apihotel.service.CheckinService;

@RestController
@RequestMapping("api/checkins")
public class CheckinController {

	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private CheckinService checkinService;

	@Autowired
	private HospedeRepository hospedeRepository;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CheckinDto> criar(@RequestBody Checkin checkin, UriComponentsBuilder uriBuilder)
			throws Exception {

		Optional<Hospede> hospede = hospedeRepository.findById(checkin.getHospede().getId());
		
		if (!hospede.isPresent()) {
			throw new Exception();
		}
		checkin.setHospede(hospede.orElse(null));
		checkinRepository.save(checkin);
		URI uri = uriBuilder.path("/api/checkins/{id}").buildAndExpand(checkin.getId()).toUri();

		return ResponseEntity.created(uri).body(new CheckinDto(checkin));

	}

	@GetMapping
	public List<CheckinDto> listarCheckins(String nomeHospede, String documentoHospede, String telefoneHospede) {

		if (nomeHospede != null) {
			List<Checkin> checkins = checkinRepository.findByHospedeNome(nomeHospede);
			return CheckinDto.converter(checkins);

		}
		if (documentoHospede != null) {
			List<Checkin> checkins = checkinRepository.findByHospedeDocumento(documentoHospede);
			return CheckinDto.converter(checkins);
		}
		if (telefoneHospede != null) {
			List<Checkin> checkins = checkinRepository.findByHospedeTelefone(telefoneHospede);
			return CheckinDto.converter(checkins);
		} else {

			List<Checkin> checkins = checkinRepository.findAll();
			return CheckinDto.converter(checkins);

		}
	}

	@GetMapping("/{idCheckin}")
	public ResponseEntity<CheckinDto> buscarPorId(@PathVariable Long idCheckin) {

		Optional<Checkin> checkin = checkinRepository.findById(idCheckin);

		if (checkin.isPresent()) {
			return ResponseEntity.ok(new CheckinDto(checkin.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PutMapping("/{idCheckin}")
	@Transactional
	public ResponseEntity<CheckinDto> atualizar(@PathVariable Long idCheckin, @RequestBody @Valid CheckinForm form) {
		Optional<Checkin> optional = checkinRepository.findById(idCheckin);
		if (optional.isPresent()) {
			Checkin checkin = form.atualizar(idCheckin, checkinRepository);

			return ResponseEntity.ok(new CheckinDto(checkin));
		}

		return ResponseEntity.notFound().build();

	}

	@DeleteMapping("/{idCheckin}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long idCheckin) {

		Optional<Checkin> optional = checkinRepository.findById(idCheckin);
		if (optional.isPresent()) {
			checkinRepository.deleteById(idCheckin);
			return ResponseEntity.ok().build();

		}
		return ResponseEntity.notFound().build();

	}

}
