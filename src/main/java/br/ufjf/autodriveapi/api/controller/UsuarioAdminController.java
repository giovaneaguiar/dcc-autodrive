package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.*;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.exception.SenhaInvalidaException;
import br.ufjf.autodriveapi.model.entity.UsuarioAdmin;
//import br.ufjf.autodriveapi.model.entity.Curso;
import br.ufjf.autodriveapi.security.JwtService;
import br.ufjf.autodriveapi.service.UsuarioAdminService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuariosAdmin")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioAdminController {

    private final UsuarioAdminService usuarioAdminService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final UsuarioAdminService service;

    @GetMapping()
    public ResponseEntity get() {
        List<UsuarioAdmin> usuariosAdmin = service.getUsuariosAdmin();
        return ResponseEntity.ok(usuariosAdmin.stream().map(UsuarioAdminDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<UsuarioAdmin> usuarioAdmin = service.getUsuarioAdminById(id);
        if (!usuarioAdmin.isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(usuarioAdmin.map(UsuarioAdminDTO::create));
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody UsuarioAdminDTO dto) {
        try {
            if (dto.getSenha() == null || dto.getSenha().trim().equals("") ||
                    dto.getSenhaRepeticao() == null || dto.getSenhaRepeticao().trim().equals("")) {
                return ResponseEntity.badRequest().body("Senha inválida");
            }
            if (!dto.getSenha().equals(dto.getSenhaRepeticao())) {
                return ResponseEntity.badRequest().body("Senhas não conferem");
            }
            UsuarioAdmin usuarioAdmin = converter(dto);
            String senhaCriptografada = passwordEncoder.encode(dto.getSenha());
            usuarioAdmin.setSenha(senhaCriptografada);
            usuarioAdmin = service.salvar(usuarioAdmin);
            return new ResponseEntity(usuarioAdmin, HttpStatus.CREATED);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/auth")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais){
        try{
            UsuarioAdmin usuarioAdmin = UsuarioAdmin.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha()).build();
            UserDetails usuarioAdminAutenticado = usuarioAdminService.autenticar(usuarioAdmin);
            String token = jwtService.gerarToken(usuarioAdmin);
            return new TokenDTO(usuarioAdmin.getLogin(), token);
        } catch (UsernameNotFoundException | SenhaInvalidaException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UsuarioAdminDTO dto) {
        if (!service.getUsuarioAdminById(id).isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            if (dto.getSenha() == null || dto.getSenha().trim().equals("") ||
                    dto.getSenhaRepeticao() == null || dto.getSenhaRepeticao().trim().equals("")) {
                return ResponseEntity.badRequest().body("Senha inválida");
            }
            if (!dto.getSenha().equals(dto.getSenhaRepeticao())) {
                return ResponseEntity.badRequest().body("Senhas não conferem");
            }
            if (!service.getUsuarioAdminById(id).isPresent()) {
            UsuarioAdmin usuarioAdmin = converter(dto);
            usuarioAdmin.setId(id);
            service.salvar(usuarioAdmin);
            return ResponseEntity.ok(usuarioAdmin);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable("id") Long id) {
        Optional<UsuarioAdmin> usuarioAdmin = service.getUsuarioAdminById(id);
        if (!usuarioAdmin.isPresent()) {
            return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            service.excluir(usuarioAdmin.get());
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public UsuarioAdmin converter(UsuarioAdminDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        UsuarioAdmin usuarioAdmin = modelMapper.map(dto, UsuarioAdmin.class);
        return usuarioAdmin;
    }
}