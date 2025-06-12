package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Veiculo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {

    private String placa;
    private String modelo;
    private String cor;
    private String ano;
    private String versao;
    private String quilometragem;
    private String descricao;
    private Double preco;
    private Boolean ativo;
    private String condicao;
    private Date anuncio;

    private Long idEmpresa;
    private Long idCategoria;
    private Long idMarca;
    private Long idOpcional;
    private Long idTipoVeiculo;
    private Long idFoto;

    private String nomeEmpresa;
    private String nomeCategoria;
    private String nomeMarca;
    private String opcional;
    private String nomeTipoVeiculo;
    private String foto;

    public static VeiculoDTO create(Veiculo veiculo) {
        ModelMapper modelMapper = new ModelMapper();
        VeiculoDTO dto = modelMapper.map(veiculo, VeiculoDTO.class);
        dto.nomeEmpresa = veiculo.getEmpresa().getNome();
        dto.nomeCategoria = veiculo.getCategoria().getDescricao();
        dto.nomeMarca = veiculo.getMarca().getNome();
        dto.opcional = veiculo.getOpcional().getDescricao();
        dto.nomeTipoVeiculo = veiculo.getTipo().getNome();
        dto.foto = veiculo.getFoto().getFoto();
        return dto;
    }
}
