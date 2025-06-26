package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.model.repository.EmpresaRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmpresaService {
    private EmpresaRepository repository;

    public EmpresaService(EmpresaRepository repository) {
        this.repository = repository;
    }

    public List<Empresa> getEmpresa() {
        return repository.findAll();
    }

    public Optional<Empresa> getEmpresaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Empresa salvar(Empresa empresa) {
        validar(empresa);
        return repository.save(empresa);
    }

    @Transactional
    public void excluir(Empresa empresa) {
        Objects.requireNonNull(empresa.getId());
        repository.delete(empresa);
    }

    public void validar(Empresa empresa) {}
}
