package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.NotificacaoDTO;
import br.ufjf.autodriveapi.api.dto.NotificacaoDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Notificacao;
import br.ufjf.autodriveapi.model.entity.Notificacao;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.service.NotificacaoService;
import br.ufjf.autodriveapi.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/notificacoes")
@CrossOrigin

public class NotificacaoController {

        private final NotificacaoService service;
        private final UsuarioService usuarioService;

        public NotificacaoController(NotificacaoService service, UsuarioService usuarioService) {
            this.service = service;
            this.usuarioService = usuarioService;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Notificacao> notificacoes = service.getNotificacao();
            return ResponseEntity.ok(notificacoes.stream().map(NotificacaoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Notificacao> notificacao = service.getNotificacaoById(id);
            if(!notificacao.isPresent()) {
                return new ResponseEntity("Notificacao não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(notificacao.map(NotificacaoDTO::create));
        }

        @PostMapping()
        public ResponseEntity post(@RequestBody NotificacaoDTO dto) {
            try {
                Notificacao notificacao = converter(dto);
                notificacao = service.salvar(notificacao);
                return new ResponseEntity(notificacao, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Notificacao converter(NotificacaoDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Notificacao notificacao = modelMapper.map(dto, Notificacao.class);
            if (dto.getIdUsuario() != null) {
                Optional<Usuario> usuario = usuarioService.getUsuarioById(dto.getIdUsuario());
                if (!usuario.isPresent()) {
                    notificacao.setUsuario(null);
                } else {
                    notificacao.setUsuario(usuario.get());
                }
            }
            return notificacao;
        }
}
