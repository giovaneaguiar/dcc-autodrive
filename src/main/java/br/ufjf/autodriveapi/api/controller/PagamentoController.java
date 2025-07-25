package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.OpcionalDTO;
import br.ufjf.autodriveapi.api.dto.PagamentoDTO;
import br.ufjf.autodriveapi.api.dto.PropostaDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.*;
import br.ufjf.autodriveapi.service.PagamentoService;
import br.ufjf.autodriveapi.service.VendaService;
import org.modelmapper.ModelMapper;
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
    private final VendaService vendaService;

    public PagamentoController(PagamentoService service, VendaService vendaService) {
            this.service = service;
        this.vendaService = vendaService;
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

        @PostMapping()
        public ResponseEntity post(@RequestBody PagamentoDTO dto) {
            try {
                Pagamento pagamento = converter(dto);
                pagamento = service.salvar(pagamento);
                return new ResponseEntity(pagamento, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody PagamentoDTO dto) {
            if (!service.getPagamentoById(id).isPresent()) {
                return new ResponseEntity("Pagamento não encontrado", HttpStatus.NOT_FOUND);
            }
            try {
                Pagamento pagamento = converter(dto);
                pagamento.setId(id);
                service.salvar(pagamento);
                return ResponseEntity.ok(pagamento);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("{id}")
        public ResponseEntity excluir(@PathVariable("id") Long id) {
            Optional<Pagamento> pagamento = service.getPagamentoById(id);
            if (!pagamento.isPresent()) {
                return new ResponseEntity("Pagamento não encontrado", HttpStatus.NOT_FOUND);
            }
            try {
                service.excluir(pagamento.get());
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Pagamento converter(PagamentoDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Pagamento pagamento = modelMapper.map(dto, Pagamento.class);

            if(dto.getIdVenda() != null){
                Optional<Venda> venda = vendaService.getVendaById(dto.getIdVenda());
                if(!venda.isPresent()){
                    pagamento.setVenda(null);
                }else{
                    pagamento.setVenda(venda.get());
                }
            }
            return pagamento;
        }
}
