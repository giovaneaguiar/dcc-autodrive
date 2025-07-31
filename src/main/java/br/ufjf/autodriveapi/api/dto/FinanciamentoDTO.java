package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Financiamento;
import org.modelmapper.ModelMapper;

public class FinanciamentoDTO {

    private Long id;

    private Double valor;
    private Integer parcela;
    private String observacao;
    private Boolean aprovado;

    private Long idVenda;
    private Double valorFinalVenda;

    public static FinanciamentoDTO create(Financiamento financiamento) {
        ModelMapper modelMapper = new ModelMapper();
        FinanciamentoDTO dto = modelMapper.map(financiamento, FinanciamentoDTO.class);
        dto.valorFinalVenda= financiamento.getVenda().getValorFinal();
        return dto;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Integer getParcela() {
        return parcela;
    }

    public void setParcela(Integer parcela) {
        this.parcela = parcela;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Boolean getAprovado() {
        return aprovado;
    }

    public void setAprovado(Boolean aprovado) {
        this.aprovado = aprovado;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Double getValorFinalVenda() {
        return valorFinalVenda;
    }

    public void setValorFinalVenda(Double valorFinalVenda) {
        this.valorFinalVenda = valorFinalVenda;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
