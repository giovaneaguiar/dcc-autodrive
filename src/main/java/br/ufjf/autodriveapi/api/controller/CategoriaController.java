package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.CategoriaDTO;
import br.ufjf.autodriveapi.model.entity.Categoria;
import br.ufjf.autodriveapi.service.CategoriaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/categorias")
@CrossOrigin

public class CategoriaController {

        private final CategoriaService service;

        public CategoriaController(CategoriaService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Categoria> categrias = service.getCategoria();
            return ResponseEntity.ok(categrias.stream().map(CategoriaDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Categoria> categoria = service.getCategoriaById(id);
            if(!categoria.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(categoria.map(CategoriaDTO::create));
        }
}