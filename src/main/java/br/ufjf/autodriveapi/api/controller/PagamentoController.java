package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.PagamentoDTO;
import br.ufjf.autodriveapi.model.entity.Pagamento;
import br.ufjf.autodriveapi.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pagamentos")
@CrossOrigin

public class PagamentoController {

        private final PagamentoService service;

        public PagamentoController(PagamentoService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Pagamento> pagamentos = service.getPagamento();
            return ResponseEntity.ok(pagamentos.stream().map(PagamentoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Pagamento> pagamento = service.getPagamentoById(id);
            if(!pagamento.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(pagamento.map(PagamentoDTO::create));
        }
}
