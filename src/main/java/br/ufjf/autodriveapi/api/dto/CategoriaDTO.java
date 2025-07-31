package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Categoria;
import org.modelmapper.ModelMapper;

public class CategoriaDTO {
    private Long id;
    private String descricao;

    public static CategoriaDTO create(Categoria categoria) {
        ModelMapper modelMapper = new ModelMapper();
        CategoriaDTO dto = modelMapper.map(categoria, CategoriaDTO.class);
        return dto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
