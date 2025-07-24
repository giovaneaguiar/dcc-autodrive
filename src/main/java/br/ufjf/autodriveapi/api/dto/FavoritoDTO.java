package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Favorito;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class FavoritoDTO {
    private Long id;

    private LocalDateTime dataFavorito;
    private String descricao;

    private Long idUsuario;
    private Long idVeiculo;

    public Long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(Long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public static FavoritoDTO create(Favorito favorito) {
        ModelMapper modelMapper = new ModelMapper();
        FavoritoDTO dto = modelMapper.map(favorito, FavoritoDTO.class);
        return dto;
    }

    public LocalDateTime getDataFavorito() {
        return dataFavorito;
    }

    public void setDataFavorito(LocalDateTime dataFavorito) {
        this.dataFavorito = dataFavorito;
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
