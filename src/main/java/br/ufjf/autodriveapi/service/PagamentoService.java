package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.PagamentoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PagamentoService {
    private PagamentoRepository repository;

    public PagamentoService(PagamentoRepository pagamento) {
        this.repository = pagamento;
    }

    public List<Pagamento> getPagamento() {
        return repository.findAll();
    }

    public Optional<Pagamento> getPagamentoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Pagamento salvar(Pagamento pagamento) {
        validar(pagamento);
        return repository.save(pagamento);
    }

    @Transactional
    public void excluir(Pagamento pagamento) {
        Objects.requireNonNull(pagamento.getId());
        repository.delete(pagamento);
    }

    public void validar(Pagamento pagamento) {}
}
