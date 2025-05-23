package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tipo extends Veiculo{
    private Long id;
    private String nome;
}
