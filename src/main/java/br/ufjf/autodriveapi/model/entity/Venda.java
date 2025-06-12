package br.ufjf.autodriveapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataVenda;
    private double valorFinal;
    private boolean concluido;
    private String descricao;
    private String status;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Veiculo veiculo;
}
