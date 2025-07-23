package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.FavoritoDTO;
import br.ufjf.autodriveapi.api.dto.FinanciamentoDTO;
import br.ufjf.autodriveapi.api.dto.FinanciamentoDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Financiamento;
import br.ufjf.autodriveapi.model.entity.Financiamento;
import br.ufjf.autodriveapi.model.entity.Venda;
import br.ufjf.autodriveapi.service.FinanciamentoService;
import br.ufjf.autodriveapi.service.VendaService;
import org.modelmapper.ModelMapper;
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
        private final VendaService vendaService;

        public FinanciamentoController(FinanciamentoService service, VendaService vendaService) {
            this.service = service;
            this.vendaService = vendaService;
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

        @PostMapping()
        public ResponseEntity post(@RequestBody FinanciamentoDTO dto) {
            try {
                Financiamento financiamento = converter(dto);
                financiamento = service.salvar(financiamento);
                return new ResponseEntity(financiamento, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FinanciamentoDTO dto) {
        if (!service.getFinanciamentoById(id).isPresent()) {
            return new ResponseEntity("Financiamento não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Financiamento financiamento = converter(dto);
            financiamento.setId(id);
            service.salvar(financiamento);
            return ResponseEntity.ok(financiamento);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        //venda e finalvenda
        public Financiamento converter(FinanciamentoDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Financiamento financiamento = modelMapper.map(dto, Financiamento.class);
            if (dto.getIdVenda() != null) {
                Optional<Venda> venda = vendaService.getVendaById(dto.getIdVenda());
                if (!venda.isPresent()) {
                    financiamento.setVenda(null);
                } else {
                    financiamento.setVenda(venda.get());
                }
            }
            return financiamento;
        }
}
