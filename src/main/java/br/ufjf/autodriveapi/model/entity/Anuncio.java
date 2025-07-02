package br.ufjf.autodriveapi.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor

public class Anuncio {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private Date dataAnuncio;
     private Double preco;
     private String descricao;
     private String foto;
     private Boolean vendido;

     public Long getId() {
          return id;
     }

     public void setId(Long id) {
          this.id = id;
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
