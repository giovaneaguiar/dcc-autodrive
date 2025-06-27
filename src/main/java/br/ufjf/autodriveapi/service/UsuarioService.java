package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.UsuarioRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioService {
    private UsuarioRepository repository;

    public UsuarioService(UsuarioRepository usuario) {
        this.repository = usuario;
    }

    public List<Usuario> getUsuario() {
        return repository.findAll();
    }

    public Optional<Usuario> getUsuarioById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Usuario salvar(Usuario usuario) {
        validar(usuario);
        return repository.save(usuario);
    }

    @Transactional
    public void excluir(Usuario usuario) {
        Objects.requireNonNull(usuario.getId());
        repository.delete(usuario);
    }

    public void validar(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().trim().isEmpty()) {
            throw new RegraNegocioException("O nome do usuário é obrigatório.");
        }

        if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
            throw new RegraNegocioException("E-mail inválido.");
        }

        if (usuario.getCpf() == null || !usuario.getCpf().matches("\\d{11}")) {
            throw new RegraNegocioException("CPF inválido. Deve conter 11 dígitos numéricos.");
        }

        if (usuario.getTipo() == null || usuario.getTipo().trim().isEmpty()) {
            throw new RegraNegocioException("O tipo do usuário é obrigatório.");
        }

        if (usuario.getLogradouro() == null || usuario.getLogradouro().trim().isEmpty()) {
            throw new RegraNegocioException("O logradouro é obrigatório.");
        }

        if (usuario.getNumero() == null || usuario.getNumero() <= 0) {
            throw new RegraNegocioException("O número da residência é obrigatório e deve ser positivo.");
        }

        if (usuario.getBairro() == null || usuario.getBairro().trim().isEmpty()) {
            throw new RegraNegocioException("O bairro é obrigatório.");
        }

        if (usuario.getCidade() == null || usuario.getCidade().trim().isEmpty()) {
            throw new RegraNegocioException("A cidade é obrigatória.");
        }

        if (usuario.getUf() == null || usuario.getUf().trim().length() != 2) {
            throw new RegraNegocioException("UF inválido. Deve conter 2 letras.");
        }

        if (usuario.getCep() == null || !usuario.getCep().matches("\\d{8}")) {
            throw new RegraNegocioException("CEP inválido. Deve conter 8 dígitos numéricos.");
        }

        if (usuario.getEmpresa() == null || usuario.getEmpresa().getId() == null) {
            throw new RegraNegocioException("O vínculo com a empresa é obrigatório.");
        }
    }

}

