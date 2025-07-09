package br.ufjf.autodriveapi.api.controller;
import br.ufjf.autodriveapi.api.dto.MarcaDTO;
import br.ufjf.autodriveapi.model.entity.Marca;
import br.ufjf.autodriveapi.service.MarcaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/marcas")
@RequiredArgsConstructor
@CrossOrigin

public class MarcaController {

        private final MarcaService service;

        @GetMapping()
        public ResponseEntity get() {
            List<Marca> marcas = service.getMarca();
            return ResponseEntity.ok(marcas.stream().map(MarcaDTO::create).collect(Collectors.toList()));
        }
}
