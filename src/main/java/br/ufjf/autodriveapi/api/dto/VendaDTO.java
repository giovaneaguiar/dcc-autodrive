package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Venda;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VendaDTO {

    private LocalDateTime dataVenda;
    private double valorFinal;
    private boolean concluido;

    private Long idUsuario;
    private Long idVeiculo;
    private String nomeVeiculo;
    private String nomeUsuario;

    public static VendaDTO create(Venda venda) {
        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);
        dto.nomeUsuario = venda.getUsuario().getNome();
        dto.nomeVeiculo = venda.getVeiculo().getDescricao();
        return dto;
    }
}
