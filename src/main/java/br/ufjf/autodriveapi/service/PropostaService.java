package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.PropostaRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PropostaService {
    private PropostaRepository repository;

    public PropostaService(PropostaRepository proposta) {
        this.repository = proposta;
    }

    public List<Proposta> getProposta() {
        return repository.findAll();
    }

    public Optional<Proposta> getPropostaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Proposta salvar(Proposta proposta) {
        validar(proposta);
        return repository.save(proposta);
    }

    @Transactional
    public void excluir(Proposta proposta) {
        Objects.requireNonNull(proposta.getId());
        repository.delete(proposta);
    }

    public void validar(Proposta proposta) {}
}
