package br.ufjf.autodriveapi.api.controller;
import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.MarcaDTO;
import br.ufjf.autodriveapi.api.dto.MarcaDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Marca;
import br.ufjf.autodriveapi.model.entity.Marca;
import br.ufjf.autodriveapi.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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

        @PostMapping()
        public ResponseEntity post(@RequestBody MarcaDTO dto) {
            try {
                Marca marca = converter(dto);
                marca = service.salvar(marca);
                return new ResponseEntity(marca, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody MarcaDTO dto) {
            if (!service.getMarcaById(id).isPresent()) {
                return new ResponseEntity("Marca não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                Marca marca = converter(dto);
                marca.setId(id);
                service.salvar(marca);
                return ResponseEntity.ok(marca);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Marca converter(MarcaDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Marca marca = modelMapper.map(dto, Marca.class);
            return marca;
        }
}
