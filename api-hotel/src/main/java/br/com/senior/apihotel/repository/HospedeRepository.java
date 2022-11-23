package br.com.senior.apihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.apihotel.model.Hospede;

@Repository
public interface HospedeRepository extends JpaRepository<Hospede, Integer>{

}
