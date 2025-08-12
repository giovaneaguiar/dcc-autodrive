package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.EmpresaDTO;
import br.ufjf.autodriveapi.api.dto.EmpresaDTO;
import br.ufjf.autodriveapi.api.dto.VeiculoDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.model.entity.Veiculo;
import br.ufjf.autodriveapi.model.repository.VeiculoRepository;
import br.ufjf.autodriveapi.service.EmpresaService;
import br.ufjf.autodriveapi.service.VeiculoService;
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
    private final VeiculoService veiculoService;
    private final VeiculoRepository veiculoRepository;

    public EmpresaController(EmpresaService service, VeiculoService veiculoService, VeiculoRepository veiculoRepository) {
            this.service = service;
        this.veiculoService = veiculoService;
        this.veiculoRepository = veiculoRepository;
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

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody EmpresaDTO dto) {
            if (!service.getEmpresaById(id).isPresent()) {
                return new ResponseEntity("Empresa não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                Empresa empresa = converter(dto);
                empresa.setId(id);
                service.salvar(empresa);
                return ResponseEntity.ok(empresa);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("{id}")
        public ResponseEntity excluir(@PathVariable("id") Long id) {
            Optional<Empresa> categoria = service.getEmpresaById(id);
            if (!categoria.isPresent()) {
                return new ResponseEntity("Empresa não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                service.excluir(categoria.get());
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @GetMapping("{id}/veiculos")
    public ResponseEntity getVeiculos(@PathVariable("id") Long id) {
        Optional<Empresa> empresa = service.getEmpresaById(id);
        if (!empresa.isPresent()) {
            return new ResponseEntity("Empresa não encontrada", HttpStatus.NOT_FOUND);
        }
        List <Veiculo> veiculos = veiculoService.getVeiculoByEmpresa(empresa);
        return ResponseEntity.ok(veiculos.stream().map(VeiculoDTO::create).collect(Collectors.toList()));
    }

        public Empresa converter(EmpresaDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Empresa empresa = modelMapper.map(dto, Empresa.class);
            return empresa;
        }

}
