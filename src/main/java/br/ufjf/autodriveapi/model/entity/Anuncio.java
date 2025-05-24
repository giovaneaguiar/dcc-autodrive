package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Anuncio {
     private Long id;
     private Date data_anuncio;
     private Double preco;
     private String descricao;
     private String fotos;
     private Boolean vendido;

}
