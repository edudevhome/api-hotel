package br.com.senior.apihotel.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.senior.apihotel.model.Hospede;
import br.com.senior.apihotel.repository.HospedeRepository;
import lombok.Data;

@Data
public class AtualizacaoHospedeForm {

	@NotNull
	@NotEmpty
	private String nome;

	@NotNull
	@NotEmpty
	private String documento;

	@NotNull
	@NotEmpty
	private String telefone;

	@SuppressWarnings("deprecation")
	public Hospede atualizar(Long hospedeId, HospedeRepository hospedeRepository) {

		Hospede hospede = hospedeRepository.getOne(hospedeId);
		hospede.setNome(this.nome);
		hospede.setDocumento(this.documento);
		hospede.setTelefone(this.telefone);

		return hospede;
	}

}
