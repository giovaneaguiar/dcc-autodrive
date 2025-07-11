package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.FavoritoDTO;
import br.ufjf.autodriveapi.model.entity.Favorito;
import br.ufjf.autodriveapi.service.FavoritoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favoritos")
@CrossOrigin

public class FavoritoController {

        private final FavoritoService service;

        public FavoritoController(FavoritoService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Favorito> favoritos = service.getFavorito();
            return ResponseEntity.ok(favoritos.stream().map(FavoritoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Favorito> favoritos = service.getFavoritoById(id);
            if(!favoritos.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(favoritos.map(FavoritoDTO::create));
        }
}
