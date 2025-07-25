package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Marca;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
public class MarcaDTO {
    private Long id;

    private String nome;

    public static MarcaDTO create(Marca marca) {
        ModelMapper modelMapper = new ModelMapper();
        MarcaDTO dto = modelMapper.map(marca, MarcaDTO.class);
        return dto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
