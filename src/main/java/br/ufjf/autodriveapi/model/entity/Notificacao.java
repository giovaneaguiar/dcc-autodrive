package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Notificacao {
    private Long id;
    private String titulo;
    private String descricao;
    private Double valor;
    private LocalDateTime dataCriacao;
}
