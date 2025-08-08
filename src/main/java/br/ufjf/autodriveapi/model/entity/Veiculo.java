package br.ufjf.autodriveapi.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@MappedSuperclass

public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    private Empresa empresa;
    @ManyToOne
    private Categoria categoria;
    @ManyToOne
    private Marca marca;
    @ManyToOne
    private Opcional opcional;
    @ManyToOne
    private Tipo tipo;
    @ManyToOne
    private Foto foto;

    public Veiculo() {
    }

    public Veiculo(Long id, String placa, String modelo, String cor, String ano, String versao, String quilometragem, String descricao, Double preco, Boolean ativo, String condicao, Date anuncio, Empresa empresa, Categoria categoria, Marca marca, Opcional opcional, Tipo tipo, Foto foto) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.versao = versao;
        this.quilometragem = quilometragem;
        this.descricao = descricao;
        this.preco = preco;
        this.ativo = ativo;
        this.condicao = condicao;
        this.anuncio = anuncio;
        this.empresa = empresa;
        this.categoria = categoria;
        this.marca = marca;
        this.opcional = opcional;
        this.foto = foto;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(String quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCondicao() {
        return condicao;
    }

    public void setCondicao(String condicao) {
        this.condicao = condicao;
    }

    public Date getAnuncio() {
        return anuncio;
    }

    public void setAnuncio(Date anuncio) {
        this.anuncio = anuncio;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Opcional getOpcional() {
        return opcional;
    }

    public void setOpcional(Opcional opcional) {
        this.opcional = opcional;
    }

    public Foto getFoto() {
        return foto;
    }

    public void setFoto(Foto foto) {
        this.foto = foto;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}

