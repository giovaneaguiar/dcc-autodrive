package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
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

    public void validar(Opcional opcional) {
        if (opcional.getDescricao() == null || opcional.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição do opcional é obrigatória.");
        }

        if (opcional.getArCondicionado() == null) {
            throw new RegraNegocioException("O ar condicionado deve ser verdadeiro ou falso.");
        }

        if (opcional.getDirecaoHidraulica() == null) {
            throw new RegraNegocioException("A direção hidráulica deve ser verdadeiro ou falso.");
        }

        if (opcional.getVidroEletrico() == null) {
            throw new RegraNegocioException("O vidro elétrico deve ser verdadeiro ou falso.");
        }

        if (opcional.getCameraRe() == null) {
            throw new RegraNegocioException("A câmera de ré deve ser verdadeiro ou falso.");
        }

        if (opcional.getSensor() == null) {
            throw new RegraNegocioException("O sensor deve ser verdadeiro ou falso.");
        }

        if (opcional.getCompleto() == null) {
            throw new RegraNegocioException("O atatus completo deve ser verdadeiro ou falso.");
        }
    }
}
