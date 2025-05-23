package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venda {
    private Long id;
    private LocalDateTime dataVenda;
    private double valorFinal;
    private boolean concluido;
}
