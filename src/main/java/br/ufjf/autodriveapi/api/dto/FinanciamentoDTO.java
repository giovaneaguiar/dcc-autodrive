package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Financiamento;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinanciamentoDTO {

    private Long idVenda;
    private Double valorFinalVenda;

    private Double valor;
    private Integer parcela;
    private String observacao;
    private Boolean aprovado;


    public static FinanciamentoDTO create(Financiamento financiamento) {
        ModelMapper modelMapper = new ModelMapper();
        FinanciamentoDTO dto = modelMapper.map(financiamento, FinanciamentoDTO.class);
        dto.valorFinalVenda= financiamento.getVenda().getValorFinal();
        return dto;
    }
}
