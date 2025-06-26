package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.TipoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TipoService {
    private TipoRepository repository;

    public TipoService(TipoRepository tipo) {
        this.repository = tipo;
    }

    public List<Tipo> getTipo() {
        return repository.findAll();
    }

    public Optional<Tipo> getTipoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Tipo salvar(Tipo tipo) {
        validar(tipo);
        return repository.save(tipo);
    }

    @Transactional
    public void excluir(Tipo tipo) {
        Objects.requireNonNull(tipo.getId());
        repository.delete(tipo);
    }

    public void validar(Tipo tipo) {}
}
