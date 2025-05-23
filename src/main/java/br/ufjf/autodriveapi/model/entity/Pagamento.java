package br.ufjf.autodriveapi.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Pagamento extends Veiculo {
    private Long id;
    private LocalDateTime dataPagamento;
    private String metodo;
    private String status;

}
