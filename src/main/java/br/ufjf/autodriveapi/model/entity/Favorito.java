package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Favorito {
    private Long id;

    private String veiculo;
    private LocalDateTime dataFavorito;

}
