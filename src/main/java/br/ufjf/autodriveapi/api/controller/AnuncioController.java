package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.service.AnuncioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/anuncios")
@CrossOrigin

public class AnuncioController {

        private final AnuncioService service;

        public AnuncioController(AnuncioService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Anuncio> anuncios = service.getAnuncio();
            return ResponseEntity.ok(anuncios.stream().map(AnuncioDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Anuncio> anuncio = service.getAnuncioById(id);
            if(!anuncio.isPresent()) {
                return new ResponseEntity("Anuncio não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(anuncio.map(AnuncioDTO::create));
        }

        @PostMapping()
        public ResponseEntity post(@RequestBody AnuncioDTO dto) {
            try {
                Anuncio anuncio = converter(dto);
                anuncio = service.salvar(anuncio);
                return new ResponseEntity(anuncio, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody AnuncioDTO dto) {
            if (!service.getAnuncioById(id).isPresent()) {
                return new ResponseEntity("Anúncio não encontrado", HttpStatus.NOT_FOUND);
            }
            try {
                Anuncio anuncio = converter(dto);
                anuncio.setId(id);
                service.salvar(anuncio);
                return ResponseEntity.ok(anuncio);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Anuncio converter(AnuncioDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Anuncio anuncio = modelMapper.map(dto, Anuncio.class);
            return anuncio;
        }
}
