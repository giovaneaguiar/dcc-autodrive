package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagamentoDTO {

    private LocalDateTime dataPagamento;
    private String metodo;
    private String status;

    public static PagamentoDTO create(Pagamento pagamento) {
        ModelMapper modelMapper = new ModelMapper();
        PagamentoDTO dto = modelMapper.map(pagamento, PagamentoDTO.class);
        return dto;
    }
}
