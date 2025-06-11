package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Opcional;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpcionalDTO {

    private String descricao;
    private Boolean arCondicionado;
    private Boolean direcaoHidraulica;
    private Boolean vidroEletrico;
    private Boolean cameraRe;
    private Boolean sensor;
    private Boolean completo;

    public static OpcionalDTO create(Opcional opcional) {
        ModelMapper modelMapper = new ModelMapper();
        OpcionalDTO dto = modelMapper.map(opcional, OpcionalDTO.class);
        return dto;
    }
}
