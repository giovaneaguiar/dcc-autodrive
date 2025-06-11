package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Notificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotificacaoDTO {

    private Long idUsuario;
    private String nomeUsuario;

    public static NotificacaoDTO create(Notificacao notificacao) {
        ModelMapper modelMapper = new ModelMapper();
        NotificacaoDTO dto = modelMapper.map(notificacao, NotificacaoDTO.class);
        dto.nomeUsuario = notificacao.getUsuario().getNome();
        return dto;
    }
}
