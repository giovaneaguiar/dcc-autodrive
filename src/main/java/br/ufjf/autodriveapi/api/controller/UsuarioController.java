package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.UsuarioDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.service.EmpresaService;
import br.ufjf.autodriveapi.service.UsuarioService;
import org.modelmapper.ModelMapper;
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
    private final EmpresaService empresaService;

    public UsuarioController(UsuarioService service, EmpresaService empresaService) {
            this.service = service;
        this.empresaService = empresaService;
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

        @PostMapping
        public ResponseEntity post(@RequestBody UsuarioDTO dto) {
            try {
                Usuario usuario = converter(dto);
                usuario = service.salvar(usuario);
                return new ResponseEntity(usuario, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody UsuarioDTO dto) {
            if (!service.getUsuarioById(id).isPresent()) {
                return new ResponseEntity("Usuário não encontrado", HttpStatus.NOT_FOUND);
            }
            try {
                Usuario usuario = converter(dto);
                usuario.setId(id);
                service.salvar(usuario);
                return ResponseEntity.ok(usuario);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Usuario converter(UsuarioDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Usuario usuario = modelMapper.map(dto, Usuario.class);
            if(dto.getIdEmpresa() != null) {
                Optional<Empresa> empresa = empresaService.getEmpresaById(dto.getIdEmpresa());
                if(!empresa.isPresent()){
                    usuario.setEmpresa(null);
                }else {
                    usuario.setEmpresa(empresa.get());
                }
            }
            return usuario;
        }
}
