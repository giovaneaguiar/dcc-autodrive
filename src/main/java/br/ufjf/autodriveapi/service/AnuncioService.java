package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.AnuncioRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnuncioService {
    private AnuncioRepository repository;

    public AnuncioService(AnuncioRepository repository) {
        this.repository = repository;
    }

    public List<Anuncio> getAnuncio() {
        return repository.findAll();
    }

    public Optional<Anuncio> getAnuncioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Anuncio salvar(Anuncio anuncio) {
        validar(anuncio);
        return repository.save(anuncio);
    }

    @Transactional
    public void excluir(Anuncio anuncio) {
        Objects.requireNonNull(anuncio.getId());
        repository.delete(anuncio);
    }

    public void validar(Anuncio anuncio) {}
}
