package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.NotificacaoDTO;
import br.ufjf.autodriveapi.api.dto.OpcionalDTO;
import br.ufjf.autodriveapi.model.entity.Notificacao;
import br.ufjf.autodriveapi.model.entity.Opcional;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.service.OpcionalService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/opcionais")
@CrossOrigin

public class OpcionalController {

        private final OpcionalService service;

        public OpcionalController(OpcionalService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Opcional> opcionais = service.getOpcional();
            return ResponseEntity.ok(opcionais.stream().map(OpcionalDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Opcional> opcional = service.getOpcionalById(id);
            if(!opcional.isPresent()) {
                return new ResponseEntity("Opcionais não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(opcional.map(OpcionalDTO::create));
        }

        @PostMapping()
        public ResponseEntity post(@RequestBody OpcionalDTO dto) {
            try {
                Opcional opcional = converter(dto);
                opcional = service.salvar(opcional);
                return new ResponseEntity(opcional, HttpStatus.CREATED);
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Opcional converter(OpcionalDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Opcional opcional = modelMapper.map(dto, Opcional.class);
            return opcional;
        }

}
