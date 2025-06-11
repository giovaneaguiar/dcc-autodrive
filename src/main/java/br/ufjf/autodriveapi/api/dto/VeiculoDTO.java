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

    private Long idEmpresa;
    private Long idVenda;

    private String nomeEmpresa;
    private Double valorFinalVenda;

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

    public static VeiculoDTO create(Veiculo veiculo) {
        ModelMapper modelMapper = new ModelMapper();
        VeiculoDTO dto = modelMapper.map(veiculo, VeiculoDTO.class);
        dto.nomeEmpresa = veiculo.getEmpresa().getNome();
        dto.valorFinalVenda = veiculo.getVenda().getValorFinal();
        return dto;
    }
}
