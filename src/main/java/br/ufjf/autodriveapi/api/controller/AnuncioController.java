package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.service.AnuncioService;
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
}
