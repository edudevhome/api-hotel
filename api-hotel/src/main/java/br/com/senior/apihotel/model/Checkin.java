package br.com.senior.apihotel.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Checkin implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private OffsetDateTime dataEntrada = OffsetDateTime.now();
	
	
	private OffsetDateTime dataSaida;
	
	@NotEmpty
	@Column(nullable = false)
	private Boolean adicionalVeiculo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospede_id")
	private Hospede hospede;

	public Checkin(OffsetDateTime dataSaida, Boolean adicionalVeiculo, Hospede hospede) {
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
		this.hospede = hospede;
	}
	
	public Checkin(Long id, OffsetDateTime dataSaida, Boolean adicionalVeiculo, Hospede hospede) {
		this.id = id;
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
		this.hospede = hospede;
	}
	
	
	


}