package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Tipo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

public class TipoDTO {

    private String nome;

    public static TipoDTO create(Tipo tipo) {
        ModelMapper modelMapper = new ModelMapper();
        TipoDTO dto = modelMapper.map(tipo, TipoDTO.class);
        return dto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
