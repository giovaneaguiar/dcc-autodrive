package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Proposta;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PropostaDTO {

    private Long idUsuario;
    private String nomeUsuario;

    public static PropostaDTO create(Proposta proposta) {
        ModelMapper modelMapper = new ModelMapper();
        PropostaDTO dto = modelMapper.map(proposta, PropostaDTO.class);
        dto.nomeUsuario = proposta.getUsuario().getNome();
        return dto;
    }
}
