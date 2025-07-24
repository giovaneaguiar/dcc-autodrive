package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Foto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

public class FotoDTO {
    private Long id;

    private String foto;

    public static FotoDTO create(Foto foto) {
        ModelMapper modelMapper = new ModelMapper();
        FotoDTO dto = modelMapper.map(foto, FotoDTO.class);
        return dto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
