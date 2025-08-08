package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.CategoriaRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoriaService {
    private CategoriaRepository repository;

    public CategoriaService(CategoriaRepository repository) {
        this.repository = repository;
    }

    public List<Categoria> getCategoria() {
        return repository.findAll();
    }

    public Optional<Categoria> getCategoriaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Categoria salvar(Categoria categoria) {
        validar(categoria);
        return repository.save(categoria);
    }

    @Transactional
    public void excluir(Categoria categoria) {
        Objects.requireNonNull(categoria.getId());
        repository.delete(categoria);
    }

    public void validar(Categoria categoria) {
        if (categoria.getDescricao() != null && categoria.getDescricao().length() > 255) {
            throw new RegraNegocioException("A descrição da proposta não pode ultrapassar 255 caracteres.");
        }
        if (categoria.getDescricao() == null || categoria.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição da categoria é obrigatório.");
        }
    }
}
