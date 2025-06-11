package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Anuncio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioDTO {

    private Date dataAnuncio;
    private Double preco;
    private String descricao;
    private String foto;
    private Boolean vendido;

    public static AnuncioDTO create(Anuncio anuncio) {
        ModelMapper modelMapper = new ModelMapper();
        AnuncioDTO dto = modelMapper.map(anuncio, AnuncioDTO.class);
        return dto;
    }
}
