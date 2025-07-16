package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.UsuarioDTO;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/usuarios")
@CrossOrigin

public class UsuarioController {

        private final UsuarioService service;

        public UsuarioController(UsuarioService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Usuario> usuarios = service.getUsuario();
            return ResponseEntity.ok(usuarios.stream().map(UsuarioDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Usuario> usuario = service.getUsuarioById(id);
            if(!usuario.isPresent()) {
                return new ResponseEntity("Usuario não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(usuario.map(UsuarioDTO::create));
        }

        //empresa
}
