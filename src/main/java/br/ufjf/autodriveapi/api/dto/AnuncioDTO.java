package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Anuncio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;

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

    public Date getDataAnuncio() {
        return dataAnuncio;
    }

    public void setDataAnuncio(Date dataAnuncio) {
        this.dataAnuncio = dataAnuncio;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Boolean getVendido() {
        return vendido;
    }

    public void setVendido(Boolean vendido) {
        this.vendido = vendido;
    }

}
