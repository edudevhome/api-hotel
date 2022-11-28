package br.com.senior.apihotel.controller.form;

import java.time.OffsetDateTime;

import br.com.senior.apihotel.model.Checkin;
import br.com.senior.apihotel.repository.CheckinRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckinForm {

	private OffsetDateTime dataSaida;
	private Boolean adicionalVeiculo;

	@SuppressWarnings("deprecation")
	public Checkin atualizar(Long idCheckin, CheckinRepository checkinRepository) {

		Checkin checkin = checkinRepository.getOne(idCheckin);

		checkin.setDataSaida(this.dataSaida);
		checkin.setAdicionalVeiculo(this.adicionalVeiculo);

		return checkin;
	}

}
