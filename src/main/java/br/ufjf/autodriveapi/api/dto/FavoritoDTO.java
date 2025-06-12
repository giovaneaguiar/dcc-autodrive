package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Favorito;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class FavoritoDTO {

    private LocalDateTime dataFavorito;
    private String descricao;

    public static FavoritoDTO create(Favorito favorito) {
        ModelMapper modelMapper = new ModelMapper();
        FavoritoDTO dto = modelMapper.map(favorito, FavoritoDTO.class);
        return dto;
    }
}
