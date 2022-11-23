package br.com.senior.apihotel.model;

import java.time.OffsetDateTime;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Checkin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private OffsetDateTime dataEntrada;
	private OffsetDateTime dataSaida;
	private Boolean adicionalVeiculo;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospede_id")
	private Hospede hospede;


}