package br.com.senior.apihotel.config.validacao;

import lombok.Data;

@Data
public class ErroDeFormularioDto {

	private String campo;
	private String erro;

	public ErroDeFormularioDto(String campo, String erro) {
		this.campo = campo;
		this.erro = erro;
	}

}
