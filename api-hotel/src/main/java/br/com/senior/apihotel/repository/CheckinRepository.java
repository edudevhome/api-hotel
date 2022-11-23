package br.com.senior.apihotel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.senior.apihotel.model.Checkin;

@Repository
public interface CheckinRepository extends JpaRepository<Checkin, Long>{

}
