package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.FotoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FotoService {
    private FotoRepository repository;

    public FotoService(FotoRepository foto) {
        this.repository = foto;
    }

    public List<Foto> getFoto() {
        return repository.findAll();
    }

    public Optional<Foto> getFotoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Foto salvar(Foto foto) {
        validar(foto);
        return repository.save(foto);
    }

    @Transactional
    public void excluir(Foto foto) {
        Objects.requireNonNull(foto.getId());
        repository.delete(foto);
    }

    public void validar(Foto foto) {
        if (foto.getFoto() == null || foto.getFoto().trim().isEmpty()) {
            throw new RegraNegocioException("A foto é obrigatório.");
        }
    }
}
