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

    public static PropostaDTO create(Proposta proposta) {
        ModelMapper modelMapper = new ModelMapper();
        PropostaDTO dto = modelMapper.map(proposta, PropostaDTO.class);
        return dto;
    }
}
