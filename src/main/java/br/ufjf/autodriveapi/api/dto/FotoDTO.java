package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Fotos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FotoDTO {

    public static FotoDTO create(Fotos foto) {
        ModelMapper modelMapper = new ModelMapper();
        FotoDTO dto = modelMapper.map(foto, FotoDTO.class);
        return dto;
    }
}
