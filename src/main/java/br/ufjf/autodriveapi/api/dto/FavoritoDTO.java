package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Favorito;
import org.modelmapper.ModelMapper;

public class FavoritoDTO {


    public static FavoritoDTO create(Favorito favorito) {
        ModelMapper modelMapper = new ModelMapper();
        FavoritoDTO dto = modelMapper.map(favorito, FavoritoDTO.class);
        return dto;
    }
}
