package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.CategoriaDTO;
import br.ufjf.autodriveapi.api.dto.CategoriaDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Categoria;
import br.ufjf.autodriveapi.model.entity.Categoria;
import br.ufjf.autodriveapi.service.CategoriaService;
import org.modelmapper.ModelMapper;
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

        @PostMapping()
        public ResponseEntity post(@RequestBody CategoriaDTO dto) {
            try {
                Categoria categoria = converter(dto);
                categoria = service.salvar(categoria);
                return new ResponseEntity(categoria, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody CategoriaDTO dto) {
            if (!service.getCategoriaById(id).isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                Categoria categoria = converter(dto);
                categoria.setId(id);
                service.salvar(categoria);
                return ResponseEntity.ok(categoria);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("{id}")
        public ResponseEntity excluir(@PathVariable("id") Long id) {
            Optional<Categoria> categoria = service.getCategoriaById(id);
            if (!categoria.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                service.excluir(categoria.get());
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Categoria converter(CategoriaDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Categoria categoria = modelMapper.map(dto, Categoria.class);
            return categoria;
        }
}