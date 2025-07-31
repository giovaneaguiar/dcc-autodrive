package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Proposta;
import org.modelmapper.ModelMapper;


public class PropostaDTO {

    private Long id;

    private String descricao;
    private Integer valor;

    private Long idUsuario;
    private String nomeUsuario;

    public static PropostaDTO create(Proposta proposta) {
        ModelMapper modelMapper = new ModelMapper();
        PropostaDTO dto = modelMapper.map(proposta, PropostaDTO.class);
        dto.nomeUsuario = proposta.getUsuario().getNome();
        return dto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
