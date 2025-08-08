package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
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

    public void validar(Favorito favorito) {

        if (favorito.getUsuario() == null || favorito.getUsuario().getId() == null) {
            throw new RegraNegocioException("É necessário informar o usuário que favoritou.");
        }

        if (favorito.getVeiculo() == null || favorito.getVeiculo().getId() == null) {
            throw new RegraNegocioException("É necessário informar o veículo favoritado.");
        }

        if (favorito.getDescricao() != null && favorito.getDescricao().length() > 255) {
            throw new RegraNegocioException("A descrição não pode ultrapassar 255 caracteres.");
        }

        if (favorito.getDescricao() == null || favorito.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição do favorito é obrigatório.");
        }
    }

}
