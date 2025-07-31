package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Tipo;
import org.modelmapper.ModelMapper;

public class TipoDTO {

    private Long id;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
