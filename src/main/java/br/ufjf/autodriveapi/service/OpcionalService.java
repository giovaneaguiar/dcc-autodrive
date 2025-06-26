package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.OpcionalRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class OpcionalService {
    private OpcionalRepository repository;

    public OpcionalService(OpcionalRepository opcional) {
        this.repository = opcional;
    }

    public List<Opcional> getOpcional() {
        return repository.findAll();
    }

    public Optional<Opcional> getOpcionalById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Opcional salvar(Opcional opcional) {
        validar(opcional);
        return repository.save(opcional);
    }

    @Transactional
    public void excluir(Opcional opcional) {
        Objects.requireNonNull(opcional.getId());
        repository.delete(opcional);
    }

    public void validar(Opcional notificacao) {}
}
