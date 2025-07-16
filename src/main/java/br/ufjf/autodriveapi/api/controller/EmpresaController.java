package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.EmpresaDTO;
import br.ufjf.autodriveapi.api.dto.EmpresaDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.service.EmpresaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/empresas")
@CrossOrigin

public class EmpresaController {

        private final EmpresaService service;

        public EmpresaController(EmpresaService service) {
            this.service = service;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Empresa> empresas = service.getEmpresa();
            return ResponseEntity.ok(empresas.stream().map(EmpresaDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Empresa> empresa = service.getEmpresaById(id);
            if(!empresa.isPresent()) {
                return new ResponseEntity("Empresa não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(empresa.map(EmpresaDTO::create));
        }

        @PostMapping()
        public ResponseEntity post(@RequestBody EmpresaDTO dto) {
            try {
                Empresa empresa = converter(dto);
                empresa = service.salvar(empresa);
                return new ResponseEntity(empresa, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Empresa converter(EmpresaDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Empresa empresa = modelMapper.map(dto, Empresa.class);
            return empresa;
        }
}
