package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
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

    public void validar(Anuncio anuncio) {
        if (anuncio.getDataAnuncio() == null) {
            throw new RegraNegocioException("A data do anuncio é obrigatório.");
        }

        if (anuncio.getPreco() == null || anuncio.getPreco() <= 0) {
            throw new RegraNegocioException("O preço é obrigatório e deve ser positivo.");
        }

        if (anuncio.getDescricao() == null || anuncio.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição do anúncio é obrigatório.");
        }

        if (anuncio.getVendido() == null) {
            throw new RegraNegocioException("O atatus vendido deve ser verdadeiro ou falso.");
        }
    }
}
