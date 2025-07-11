package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.FinanciamentoDTO;
import br.ufjf.autodriveapi.model.entity.Financiamento;
import br.ufjf.autodriveapi.service.FinanciamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/financiamentos")
@CrossOrigin

public class FinanciamentoController {

        private final FinanciamentoService service;

        public FinanciamentoController(FinanciamentoService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Financiamento> financiamentos = service.getFinanciamento();
            return ResponseEntity.ok(financiamentos.stream().map(FinanciamentoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Financiamento> financiamento = service.getFinanciamentoById(id);
            if(!financiamento.isPresent()) {
                return new ResponseEntity("Financiamento não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(financiamento.map(FinanciamentoDTO::create));
        }
}
