package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TipoDTO {

    public static TipoDTO create(Tipo tipo) {
        ModelMapper modelMapper = new ModelMapper();
        TipoDTO dto = modelMapper.map(tipo, TipoDTO.class);
        return dto;
    }
}
