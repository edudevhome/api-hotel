package br.com.senior.apihotel.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senior.apihotel.dto.CheckinDto;
import br.com.senior.apihotel.dto.CheckinForm;
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
	public ResponseEntity<CheckinDto> criar(@RequestBody CheckinForm checkinForm, UriComponentsBuilder uriBuilder) throws Exception {

		// checkin.setDataEntrada(LocalDateTime.now());
		// hospedeRepository.

//				Checkin checkinSalvo = checkinRepository.save(checkin);
//				
//				Hospede hospede = hospedeRepository.findById(checkin.getHospede().getId());
//				hospede.getCheckins().add(checkinSalvo);
//				hospedeRepository.save(hospede);
//				
//				return checkinSalvo;

//		Optional<Hospede> hospede = hospedeRepository.findById(checkin.getHospede().getId());
//		if (!hospede.isPresent())
//			throw new Exception();
//
//		checkin.setHospede(hospede.orElse(null));
		Checkin checkin = checkinForm.converter(hospedeRepository);
		checkinRepository.save(checkin);

		URI uri = uriBuilder.path("/api/checkins/{id}").buildAndExpand(checkin.getId()).toUri();
		return ResponseEntity.created(uri).body(new CheckinDto(checkin));
//		return checkinRepository.save(checkin);
	}

	@GetMapping
	public List<CheckinDto> listarCheckins(String nomeHospede, String documentoHospede, String telefoneHospede) {

		if (nomeHospede != null) {
			List<Checkin> checkins = checkinRepository.findByHospedeNome(nomeHospede);
			return CheckinDto.converter(checkins);

		} else if (documentoHospede != null) {
			List<Checkin> checkins = checkinRepository.findByHospedeNome(documentoHospede);
			return CheckinDto.converter(checkins);
		} else if (telefoneHospede != null) {
			List<Checkin> checkins = checkinRepository.findByHospedeNome(telefoneHospede);
			return CheckinDto.converter(checkins);
		} else {

			List<Checkin> checkins = checkinRepository.findAll();
			return CheckinDto.converter(checkins);

		}
	}
	
}
