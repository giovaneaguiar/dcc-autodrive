package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
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

    public void validar(Empresa empresa) {
        if (empresa.getNome() == null || empresa.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome da empresa é obrigatório.");
        }

        if (empresa.getCnpj() == null || !empresa.getCnpj().matches("\\d{14}")) {
            throw new RegraNegocioException("CNPJ inválido. Deve conter 14 dígitos numéricos.");
        }

        if (empresa.getLogradouro() == null || empresa.getLogradouro().trim().isEmpty()) {
            throw new RegraNegocioException("O logradouro da empresa é obrigatório.");
        }

        if (empresa.getNumero() == null || empresa.getNumero() < 0) {
            throw new RegraNegocioException("O número da empresa é obrigatório e deve ser maior ou igual a zero.");
        }

        if (empresa.getBairro() == null || empresa.getBairro().trim().isEmpty()) {
            throw new RegraNegocioException("O bairro da empresa é obrigatório.");
        }

        if (empresa.getCidade() == null || empresa.getCidade().trim().isEmpty()) {
            throw new RegraNegocioException("A cidade da empresa é obrigatória.");
        }

        if (empresa.getUf() == null || empresa.getUf().trim().length() != 2) {
            throw new RegraNegocioException("UF inválido. Deve conter exatamente 2 letras.");
        }

        if (empresa.getCep() == null || !empresa.getCep().matches("\\d{8}")) {
            throw new RegraNegocioException("CEP inválido. Deve conter 8 dígitos numéricos.");
        }
    }

}
