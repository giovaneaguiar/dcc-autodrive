package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.VendaRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaService {
    private VendaRepository repository;

    public VendaService(VendaRepository venda) {
        this.repository = venda;
    }

    public List<Venda> getVenda() {
        return repository.findAll();
    }

    public Optional<Venda> getVendaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Venda salvar(Venda venda) {
        validar(venda);
        return repository.save(venda);
    }

    @Transactional
    public void excluir(Venda venda) {
        Objects.requireNonNull(venda.getId());
        repository.delete(venda);
    }

    public void validar(Venda venda) {}
}
