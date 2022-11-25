package br.com.senior.apihotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.apihotel.model.Checkin;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long>{

	// Hospede é a entidade do relacionamento e Nome é o atributo.
	List<Checkin> findByHospedeNome(String nomeHospede);
}
