package br.com.senior.apihotel.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.senior.apihotel.exception.NegocioException;
import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.HospedeRepository;

@Service
public class HospedeService {

	@Autowired
	private HospedeRepository hospedeRepository;

	@Transactional
	public Hospede salvar(Hospede hospede) {

		boolean documentoCadastrado = hospedeRepository.findByDocumento(hospede.getDocumento()).stream()
				.anyMatch(hospedeExistente -> !hospedeExistente.equals(hospede));

		if (documentoCadastrado) {
			throw new NegocioException("Documento já cadastrado!");

		}

		return hospedeRepository.save(hospede);
	}
	
	public void excluir(Long hospedeId) {
		hospedeRepository.deleteById(hospedeId);
	}

	public Hospede buscar(Long hospedeId) {
		
		return hospedeRepository.findById(hospedeId)
				.orElseThrow(() -> new NegocioException("Hospede não encontrado!"));
	}
}
