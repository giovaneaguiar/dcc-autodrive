package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.FinanciamentoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FinanciamentoService {
    private FinanciamentoRepository repository;

    public FinanciamentoService(FinanciamentoRepository financiamento) {
        this.repository = financiamento;
    }

    public List<Financiamento> getFinanciamento() {
        return repository.findAll();
    }

    public Optional<Financiamento> getFinanciamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Financiamento salvar(Financiamento financiamento) {
        validar(financiamento);
        return repository.save(financiamento);
    }

    @Transactional
    public void excluir(Financiamento financiamento) {
        Objects.requireNonNull(financiamento.getId());
        repository.delete(financiamento);
    }

    public void validar(Financiamento financiamento) {
        if (financiamento.getValor() == null || financiamento.getValor() <= 0) {
            throw new RegraNegocioException("O valor do financiamento deve ser maior que zero.");
        }

        if (financiamento.getParcela() == null || financiamento.getParcela() <= 0) {
            throw new RegraNegocioException("A quantidade de parcelas deve ser maior que zero.");
        }

        if (financiamento.getVenda() == null || financiamento.getVenda().getId() == null) {
            throw new RegraNegocioException("O financiamento deve estar associado a uma venda.");
        }

        if (financiamento.getObservacao() != null && financiamento.getObservacao().length() > 255) {
            throw new RegraNegocioException("A observação não pode ultrapassar 255 caracteres.");
        }

        if (financiamento.getObservacao() == null || financiamento.getObservacao().trim().isEmpty()) {
            throw new RegraNegocioException("A observação do favorito é obrigatório.");
        }

        if (financiamento.getAprovado() == true || financiamento.getAprovado() == false) {
            throw new RegraNegocioException("O atatus aprovado deve ser verdadeiro ou falso.");
        }

    }

}
