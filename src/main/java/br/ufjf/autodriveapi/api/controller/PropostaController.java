package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.PropostaDTO;
import br.ufjf.autodriveapi.model.entity.Proposta;
import br.ufjf.autodriveapi.service.PropostaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/propostas")
@CrossOrigin

public class PropostaController {

        private final PropostaService service;

        public PropostaController(PropostaService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Proposta> propostas = service.getProposta();
            return ResponseEntity.ok(propostas.stream().map(PropostaDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Proposta> proposta = service.getPropostaById(id);
            if(!proposta.isPresent()) {
                return new ResponseEntity("Proposta não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(proposta.map(PropostaDTO::create));
        }

        //usuario
}
