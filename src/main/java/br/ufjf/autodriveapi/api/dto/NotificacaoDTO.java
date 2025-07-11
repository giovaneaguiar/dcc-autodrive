package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Notificacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;

public class NotificacaoDTO {
    private String titulo;
    private String descricao;
    private Double valor;
    private LocalDateTime dataCriacao;

    private Long idUsuario;
    private String nomeUsuario;

    public static NotificacaoDTO create(Notificacao notificacao) {
        ModelMapper modelMapper = new ModelMapper();
        NotificacaoDTO dto = modelMapper.map(notificacao, NotificacaoDTO.class);
        dto.nomeUsuario = notificacao.getUsuario().getNome();
        return dto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
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
}
