package br.com.senior.apihotel.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.senior.apihotel.model.Hospede;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HospedeDto {

	private Long id;
	private String nome;
	private String documento;
	private String telefone;
	
	public HospedeDto(Hospede hospede) {
		this.id = hospede.getId();
		this.nome = hospede.getNome();
		this.documento = hospede.getDocumento();
		this.telefone = hospede.getTelefone();
	}
	
	public static List<HospedeDto> converter(List<Hospede> hospedes) {
		return hospedes.stream().map(HospedeDto::new).collect(Collectors.toList());
	}
}
