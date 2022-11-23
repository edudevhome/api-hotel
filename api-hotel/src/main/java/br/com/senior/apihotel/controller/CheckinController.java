package br.com.senior.apihotel.controller;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.senior.apihotel.model.Checkin;
import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.CheckinRepository;
import br.com.senior.apihotel.repository.HospedeRepository;

@RestController
@RequestMapping("api/checkins")
@Transactional
public class CheckinController {

	@Autowired
	private CheckinRepository checkinRepository;
	
	@Autowired
	private HospedeRepository hospedeRepository; 
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Checkin criar (@RequestBody Checkin checkin) throws Exception {
		
		//checkin.setDataEntrada(LocalDateTime.now());
				//hospedeRepository.
				
//				Checkin checkinSalvo = checkinRepository.save(checkin);
//				
//				Hospede hospede = hospedeRepository.findById(checkin.getHospede().getId());
//				hospede.getCheckins().add(checkinSalvo);
//				hospedeRepository.save(hospede);
//				
//				return checkinSalvo;
				
				Optional<Hospede> hospede = hospedeRepository.findById(checkin.getHospede().getId());
				if (!hospede.isPresent())
					throw new Exception();
				
				checkin.setHospede(hospede.orElse(null));
				return checkinRepository.save(checkin);
		
//		return checkinRepository.save(checkin);
	}
}
