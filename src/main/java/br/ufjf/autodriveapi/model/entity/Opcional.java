package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Opcionais extends Veiculo {
    private Long id;
    private String descricao;
    private Boolean arCondicionado;
    private Boolean direcaoHidraulica;
    private Boolean vidroEletrico;
    private Boolean cameraRe;
    private Boolean sensor;
    private Boolean completo;

}
