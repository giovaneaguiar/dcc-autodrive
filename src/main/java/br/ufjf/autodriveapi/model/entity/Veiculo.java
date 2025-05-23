package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Veiculo {
    private Long id;
    private String placa;
    private String modelo;
    private String cor;
    private String ano;
    private String versao;
    private String quilometragem;
    private String descricao;
    private Double preco;
    private Boolean ativo;
    private String condicao;
    private Date anuncio;
}

