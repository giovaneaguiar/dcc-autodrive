package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.VendaDTO;
import br.ufjf.autodriveapi.model.entity.Venda;
import br.ufjf.autodriveapi.service.VendaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vendas")
@CrossOrigin

public class VendaController {

        private final VendaService service;

        public VendaController(VendaService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Venda> vendas = service.getVenda();
            return ResponseEntity.ok(vendas.stream().map(VendaDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Venda> venda = service.getVendaById(id);
            if(!venda.isPresent()) {
                return new ResponseEntity("Vendas não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(venda.map(VendaDTO::create));
        }

        //usuario e veiculo
}
