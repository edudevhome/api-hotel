package br.com.senior.apihotel.dto;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.senior.apihotel.model.Checkin;
import br.com.senior.apihotel.model.Hospede;
import lombok.Data;

@Data
public class CheckinDto {

	private Long id;
	private OffsetDateTime dataEntrada;
	private OffsetDateTime dataSaida;
	private Boolean adicionalVeiculo;
	private String nomeHospede;
	private String documentoHospede;
	private String telefoneHospede;
	
	public CheckinDto(Checkin checkin) {
		this.id = checkin.getId();
		this.dataEntrada = checkin.getDataEntrada();
		this.dataSaida = checkin.getDataSaida();
		this.adicionalVeiculo = checkin.getAdicionalVeiculo();
		this.nomeHospede = checkin.getHospede().getNome();
		this.documentoHospede = checkin.getHospede().getDocumento();
		this.telefoneHospede = checkin.getHospede().getTelefone();
	}
	
	public static List<CheckinDto> converter(List<Checkin> checkins) {
		return checkins.stream().map(CheckinDto ::new).collect(Collectors.toList());
	}
}