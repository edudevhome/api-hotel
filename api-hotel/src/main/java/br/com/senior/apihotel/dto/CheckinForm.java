package br.com.senior.apihotel.dto;

import java.time.OffsetDateTime;
import java.util.Optional;

import br.com.senior.apihotel.model.Checkin;
import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.HospedeRepository;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CheckinForm {

	private OffsetDateTime dataSaida;
	private Boolean adicionalVeiculo;
	private Long idHospede;
	
//	public CheckinForm(Checkin checkin) {
//		this.dataSaida = checkin.getDataSaida();
//		this.adicionalVeiculo = checkin.getAdicionalVeiculo();
//		this.idHospede = checkin.getHospede().getId();
//	}
//	
	
	public Checkin converter(HospedeRepository hospedeRepository) {
		Optional<Hospede> hospede = hospedeRepository.findById(idHospede);
		return new Checkin(dataSaida, adicionalVeiculo, idHospede);
	} 
}
