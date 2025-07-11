package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.NotificacaoDTO;
import br.ufjf.autodriveapi.model.entity.Notificacao;
import br.ufjf.autodriveapi.service.NotificacaoService;
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

        public NotificacaoController(NotificacaoService service) {
            this.service = service;
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
}
