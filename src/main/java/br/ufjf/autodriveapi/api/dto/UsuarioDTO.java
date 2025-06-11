package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    private Long idEmpresa;
    private String nomeEmpresa;

    public static UsuarioDTO create(Usuario usuario) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioDTO dto = modelMapper.map(usuario, UsuarioDTO.class);
        dto.nomeEmpresa = usuario.getEmpresa().getNome();
        return dto;
    }
}
