package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Anuncio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDTO {

    public static AnuncioDTO create(Anuncio anuncio) {
        ModelMapper modelMapper = new ModelMapper();
        AnuncioDTO dto = modelMapper.map(anuncio, AnuncioDTO.class);
        return dto;
    }
}
