package br.com.senior.apihotel.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import br.com.senior.apihotel.dto.HospedeDto;
import br.com.senior.apihotel.model.Hospede;

@Component
public class HospedeAssembler {

private ModelMapper modelMapper = new ModelMapper();
	
	public HospedeDto toModel(Hospede hospede) {
		return modelMapper.map(hospede, HospedeDto.class);
	}
	
	public List<HospedeDto> toCollectionmodel(List<Hospede> hospedes){
		return hospedes.stream()
				.map(this:: toModel)
				.collect(Collectors.toList());
	}
	
	public Hospede toEntity(HospedeDto hospedeDto) {
		return modelMapper.map(hospedeDto, Hospede.class);
	}
}
