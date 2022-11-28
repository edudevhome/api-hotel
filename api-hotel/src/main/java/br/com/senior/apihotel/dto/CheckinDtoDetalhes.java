package br.com.senior.apihotel.dto;

import java.time.OffsetDateTime;

import br.com.senior.apihotel.model.StatusHospede;
import lombok.Data;

@Data
public class CheckinDtoDetalhes {

	private Long id;
	private OffsetDateTime dataEntrada;
	private OffsetDateTime dataSaida;
	private Boolean adicionalVeiculo;
	private String nomeHospede;
	private String documentoHospede;
	private String telefoneHospede;
	private StatusHospede status;
}
