package br.ufjf.autodriveapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Anuncio {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private Date dataAnuncio;
     private Double preco;
     private String descricao;
     private String foto;
     private Boolean vendido;

}
