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

    private Long idUsuario;
    private String nomeUsuario;

    private LocalDateTime dataVenda;
    private double valorFinal;
    private boolean concluido;

    public static VendaDTO create(Venda venda) {
        ModelMapper modelMapper = new ModelMapper();
        VendaDTO dto = modelMapper.map(venda, VendaDTO.class);
        dto.nomeUsuario = venda.getUsuario().getNome();
        return dto;
    }
}
