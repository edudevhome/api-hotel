package br.com.senior.apihotel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.apihotel.model.Checkin;
import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.CheckinRepository;

@Service
public class CheckinService {

	@Autowired
	private CheckinRepository checkinRepository;

	@Autowired
	private HospedeService hospedeService;

	@Transactional
	public Checkin salvar(Checkin checkin) {

		Hospede hospede = hospedeService.buscar(checkin.getHospede().getId());

		checkin.setHospede(hospede);

		return checkinRepository.save(checkin);

	}
}
