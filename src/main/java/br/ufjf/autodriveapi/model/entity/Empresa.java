package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Empresa {
    private Long id;
    private String nome;
    private String cnpj;
    private Usuario usuario;
    private Veiculo veiculo;
}
