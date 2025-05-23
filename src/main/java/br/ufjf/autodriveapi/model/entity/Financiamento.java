package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Financiamento {
    private Long id;
    private Double valor;
    private Integer parcelas;
    private String observacao;
    private Boolean aprovado;

    private Venda venda;
}
