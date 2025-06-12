package br.ufjf.autodriveapi.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Opcional{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private Boolean arCondicionado;
    private Boolean direcaoHidraulica;
    private Boolean vidroEletrico;
    private Boolean cameraRe;
    private Boolean sensor;
    private Boolean completo;
}
