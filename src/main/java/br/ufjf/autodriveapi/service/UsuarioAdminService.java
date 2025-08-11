package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.exception.SenhaInvalidaException;
import br.ufjf.autodriveapi.model.entity.Aluno;
import br.ufjf.autodriveapi.model.entity.Curso;
import br.ufjf.autodriveapi.model.entity.Professor;
import br.ufjf.autodriveapi.model.entity.UsuarioAdmin;
import br.ufjf.autodriveapi.model.repository.UsuarioAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioAdminService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UsuarioAdminRepository repository;

    public List<UsuarioAdmin> getUsuariosAdmin() {
        return repository.findAll();
    }

    public Optional<UsuarioAdmin> getUsuarioAdminById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public UsuarioAdmin salvar(UsuarioAdmin usuarioAdmin){
        validar(usuarioAdmin);
        return repository.save(usuarioAdmin);
    }

    public UserDetails autenticar(UsuarioAdmin usuarioAdmin){
        UserDetails user = loadUserByUsername(usuarioAdmin.getLogin());
        boolean senhasBatem = encoder.matches(usuarioAdmin.getSenha(), user.getPassword());

        if (senhasBatem){
            return user;
        }
        throw new SenhaInvalidaException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UsuarioAdmin usuarioAdmin = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String[] roles = usuarioAdmin.getAdmin()
                ? new String[]{"ADMIN", "USER"}
                : new String[]{"USER"};

        return User
                .builder()
                .username(usuarioAdmin.getLogin())
                .password(usuarioAdmin.getSenha())
                .roles(roles)
                .build();
    }

    @Transactional
    public void excluir(UsuarioAdmin usuarioAdmin) {
        Objects.requireNonNull(usuarioAdmin.getId());
        repository.delete(usuarioAdmin);
    }

    public void validar(UsuarioAdmin usuarioAdmin) {
        if (usuarioAdmin.getLogin() == null || usuarioAdmin.getLogin().trim().equals("")) {
            throw new RegraNegocioException("Login inválido");
        }
        if (usuarioAdmin.getCpf() == null || usuarioAdmin.getCpf().trim().equals("")) {
            throw new RegraNegocioException("CPF inválido");
        }
    }
}