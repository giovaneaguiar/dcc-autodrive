package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.MarcaRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MarcaService {
    private MarcaRepository repository;

    public MarcaService(MarcaRepository marca) {
        this.repository = marca;
    }

    public List<Marca> getMarca() {
        return repository.findAll();
    }

    public Optional<Marca> getMarcaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Marca salvar(Marca marca) {
        validar(marca);
        return repository.save(marca);
    }

    @Transactional
    public void excluir(Marca marca) {
        Objects.requireNonNull(marca.getId());
        repository.delete(marca);
    }

    public void validar(Marca marca) {
        if (marca.getNome() == null || marca.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da marca é obrigatório.");
        }
    }
}
