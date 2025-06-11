package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Empresa;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {

    private String nome;
    private String cnpj;

    public static EmpresaDTO create(Empresa empresa) {
        ModelMapper modelMapper = new ModelMapper();
        EmpresaDTO dto = modelMapper.map(empresa, EmpresaDTO.class);
        return dto;
    }
}
