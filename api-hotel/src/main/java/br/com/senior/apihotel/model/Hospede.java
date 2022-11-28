package br.com.senior.apihotel.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Hospede implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(length = 60, nullable = false, unique = false)
	private String nome;
	
	@NotEmpty
	@Column(length = 5, nullable = false, unique = true)
	private String documento;
	
	@NotEmpty
	@Column(length = 14, nullable = false, unique = true)
	private String telefone;

	@OneToMany(mappedBy = "hospede")
	private List<Checkin> checkins = new ArrayList<>();
	
	public Hospede(Long id, String nome, String documento, String telefone) {
		this.id = id;
		this.nome = nome;
		this.documento = documento;
		this.telefone = telefone;
	}

}
