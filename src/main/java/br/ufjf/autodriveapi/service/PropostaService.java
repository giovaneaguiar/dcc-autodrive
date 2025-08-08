package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
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

    public void validar(Proposta proposta) {
        if (proposta.getValor() == null || proposta.getValor() <= 0) {
            throw new RegraNegocioException("O valor da proposta deve ser maior que zero.");
        }

        if (proposta.getUsuario() == null || proposta.getUsuario().getId() == null) {
            throw new RegraNegocioException("A proposta deve estar associada a um usuário.");
        }

        if (proposta.getDescricao() != null && proposta.getDescricao().length() > 255) {
            throw new RegraNegocioException("A descrição da proposta não pode ultrapassar 255 caracteres.");
        }

        if (proposta.getDescricao() == null || proposta.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição da categoria é obrigatório.");
        }
    }

}
