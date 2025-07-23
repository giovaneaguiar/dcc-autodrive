package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.TipoDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Tipo;
import br.ufjf.autodriveapi.service.TipoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tipos")
@CrossOrigin

public class TipoController {

        private final TipoService service;

        public TipoController(TipoService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Tipo> tipos = service.getTipo();
            return ResponseEntity.ok(tipos.stream().map(TipoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Tipo> tipo = service.getTipoById(id);
            if(!tipo.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(tipo.map(TipoDTO::create));
        }

        @PostMapping
        public ResponseEntity post(@RequestBody TipoDTO dto) {
            try{
                Tipo tipo = converter(dto);
                tipo = service.salvar(tipo);
                return new ResponseEntity(tipo, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody TipoDTO dto) {
            if (!service.getTipoById(id).isPresent()) {
                return new ResponseEntity("Tipo não encontrado", HttpStatus.NOT_FOUND);
            }
            try {
                Tipo tipo = converter(dto);
                tipo.setId(id);
                service.salvar(tipo);
                return ResponseEntity.ok(tipo);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        public Tipo converter (TipoDTO dto){
            ModelMapper modelMapper = new ModelMapper();
            Tipo tipo = modelMapper.map(dto, Tipo.class);
            return tipo;
        }
}
