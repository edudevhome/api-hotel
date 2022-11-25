package br.com.senior.apihotel.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

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
	private Boolean adicionalVeiculo;
	
	@Transient
	private Long idHospede;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "hospede_id")
	private Hospede hospede;

	public Checkin(OffsetDateTime dataSaida, Boolean adicionalVeiculo, Long idHospede) {
		this.dataSaida = dataSaida;
		this.adicionalVeiculo = adicionalVeiculo;
		this.idHospede = idHospede;
	}
	
	
	


}