package br.ufjf.autodriveapi.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Financiamento {
    private Long id;
    private Double valor;
    private Integer parcela;
    private String observacao;
    private Boolean aprovado;

    @OneToOne
    private Venda venda;
}
