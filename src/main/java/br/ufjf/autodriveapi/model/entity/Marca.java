package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Marca extends Veiculo{
    private Long id;
    private String marca;
}
