package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MarcaDTO {

    private String marca;

    public static MarcaDTO create(Marca marca) {
        ModelMapper modelMapper = new ModelMapper();
        MarcaDTO dto = modelMapper.map(marca, MarcaDTO.class);
        return dto;
    }
}
