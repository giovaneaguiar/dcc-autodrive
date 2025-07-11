package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.FotoDTO;
import br.ufjf.autodriveapi.model.entity.Foto;
import br.ufjf.autodriveapi.service.FotoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/fotos")
@CrossOrigin

public class FotoController {

        private final FotoService service;

        public FotoController(FotoService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Foto> fotos = service.getFoto();
            return ResponseEntity.ok(fotos.stream().map(FotoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Foto> foto = service.getFotoById(id);
            if(!foto.isPresent()) {
                return new ResponseEntity("Foto não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(foto.map(FotoDTO::create));
        }
}
