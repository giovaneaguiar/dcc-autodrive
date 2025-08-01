package br.ufjf.autodriveapi.api.dto;

import br.ufjf.autodriveapi.model.entity.Pagamento;
import org.modelmapper.ModelMapper;
import java.time.LocalDateTime;

public class PagamentoDTO {

    private Long id;

    private String status;
    private String metodo;
    private LocalDateTime dataPagamento;
    private String descricao;

    private Long idVenda;
    private Double valorFinal;

    public static PagamentoDTO create(Pagamento pagamento) {
        ModelMapper modelMapper = new ModelMapper();
        PagamentoDTO dto = modelMapper.map(pagamento, PagamentoDTO.class);
        dto.valorFinal = pagamento.getVenda().getValorFinal();
        return dto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public Double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(Double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
