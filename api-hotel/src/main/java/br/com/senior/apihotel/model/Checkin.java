package br.com.senior.apihotel.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class Checkin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private OffsetDateTime entrada;
	
	@ManyToOne
	private Hospede hospede;

	public Checkin() {
	}

	public Checkin(OffsetDateTime entrada, Hospede hospede) {
		this.entrada = entrada;
		this.hospede = hospede;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OffsetDateTime getEntrada() {
		return entrada;
	}

	public void setEntrada(OffsetDateTime entrada) {
		this.entrada = entrada;
	}

	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

}
