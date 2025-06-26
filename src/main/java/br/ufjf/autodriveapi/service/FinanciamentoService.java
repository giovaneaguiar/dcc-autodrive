package br.ufjf.autodriveapi.service;

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

    public void validar(Financiamento financiamento) {}
}
