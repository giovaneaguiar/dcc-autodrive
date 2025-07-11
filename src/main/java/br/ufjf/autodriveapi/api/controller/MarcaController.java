package br.ufjf.autodriveapi.api.controller;
import br.ufjf.autodriveapi.api.dto.MarcaDTO;
import br.ufjf.autodriveapi.model.entity.Marca;
import br.ufjf.autodriveapi.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/marcas")
@CrossOrigin

public class MarcaController {

        private final MarcaService service;

        public MarcaController(MarcaService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Marca> marcas = service.getMarca();
            return ResponseEntity.ok(marcas.stream().map(MarcaDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Marca> marca = service.getMarcaById(id);
            if(!marca.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(marca.map(MarcaDTO::create));
        }
}
