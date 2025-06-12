package br.ufjf.autodriveapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass

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
    @OneToOne
    private Categoria categoria;
    @OneToOne
    private Marca marca;
    @OneToOne
    private Opcional opcional;
    @OneToOne
    private Tipo tipo;
    @OneToOne
    private Foto foto;
}

