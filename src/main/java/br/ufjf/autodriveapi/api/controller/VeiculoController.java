package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.VeiculoDTO;
import br.ufjf.autodriveapi.model.entity.Veiculo;
import br.ufjf.autodriveapi.service.VeiculoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/veiculos")
@CrossOrigin

public class VeiculoController {

        private final VeiculoService service;

        public VeiculoController(VeiculoService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Veiculo> veiculos = service.getVeiculo();
            return ResponseEntity.ok(veiculos.stream().map(VeiculoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Veiculo> veiculo = service.getVeiculoById(id);
            if(!veiculo.isPresent()) {
                return new ResponseEntity("Veiculo não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(veiculo.map(VeiculoDTO::create));
        }
}
