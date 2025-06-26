package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.FavoritoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FavoritoService {
    private FavoritoRepository repository;

    public FavoritoService(FavoritoRepository repository) {
        this.repository = repository;
    }

    public List<Favorito> getFavorito() {
        return repository.findAll();
    }

    public Optional<Favorito> getFavoritoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Favorito salvar(Favorito favorito) {
        validar(favorito);
        return repository.save(favorito);
    }

    @Transactional
    public void excluir(Favorito favorito) {
        Objects.requireNonNull(favorito.getId());
        repository.delete(favorito);
    }

    public void validar(Favorito favorito) {}
}
