package br.com.senior.apihotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.apihotel.model.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Long>{

	List<Hospede> findByDocumento(String documento);

	Hospede findByNome(String nomeHospede);
}
