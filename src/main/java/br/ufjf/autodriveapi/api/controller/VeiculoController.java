package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.VeiculoDTO;
import br.ufjf.autodriveapi.model.entity.*;
import br.ufjf.autodriveapi.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/veiculos")
@CrossOrigin

public class VeiculoController {

    private final VeiculoService service;
    private final EmpresaService empresaService;
    private final CategoriaService categoriaService;
    private final MarcaService marcaService;
    private final OpcionalService opcionalService;
    private final TipoService tipoService;
    private final FotoService fotoService;

    public VeiculoController(VeiculoService service, EmpresaService empresaService, CategoriaService categoriaService, MarcaService marcaService, OpcionalService opcionalService, TipoService tipoService, FotoService fotoService) {
        this.service = service;
        this.empresaService = empresaService;
        this.categoriaService = categoriaService;
        this.marcaService = marcaService;
        this.opcionalService = opcionalService;
        this.tipoService = tipoService;
        this.fotoService = fotoService;
    }

    @GetMapping()
    public ResponseEntity get() {
        List<Veiculo> veiculos = service.getVeiculo();
        return ResponseEntity.ok(veiculos.stream().map(VeiculoDTO::create).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<Veiculo> veiculo = service.getVeiculoById(id);
        if (!veiculo.isPresent()) {
            return new ResponseEntity("Veiculo não encontrada", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(veiculo.map(VeiculoDTO::create));
    }

    @PostMapping
    public ResponseEntity post(@RequestBody VeiculoDTO dto) {
        try {
            Veiculo veiculo = converter(dto);
            veiculo = service.salvar(veiculo);
            return new ResponseEntity(veiculo, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    public Veiculo converter(VeiculoDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        Veiculo veiculo = modelMapper.map(dto, Veiculo.class);
        if (dto.getIdEmpresa() != null) {
            Optional<Empresa> empresa = empresaService.getEmpresaById(dto.getIdEmpresa());
            if (!empresa.isPresent()) {
                veiculo.setEmpresa(null);
            } else {
                veiculo.setEmpresa(empresa.get());
            }
        }
        if (dto.getIdCategoria() != null) {
            Optional<Categoria> categoria = categoriaService.getCategoriaById(dto.getIdCategoria());
            if (!categoria.isPresent()) {
                veiculo.setCategoria(null);
            } else {
                veiculo.setCategoria(categoria.get());
            }
        }
        if (dto.getIdMarca() != null) {
            Optional<Marca> marca = marcaService.getMarcaById(dto.getIdMarca());
            if (!marca.isPresent()) {
                veiculo.setMarca(null);
            } else {
                veiculo.setMarca(marca.get());
            }
        }
        if (dto.getIdOpcional() != null) {
            Optional<Opcional> opcional = opcionalService.getOpcionalById(dto.getIdOpcional());
            if (!opcional.isPresent()) {
                veiculo.setOpcional(null);
            } else {
                veiculo.setOpcional(opcional.get());
            }

            if (dto.getIdTipoVeiculo() != null) {
                Optional<Tipo> tipo = tipoService.getTipoById(dto.getIdTipoVeiculo());
                if (!tipo.isPresent()) {
                    veiculo.setTipo(null);
                } else {
                    veiculo.setTipo(tipo.get());
                }
            }

            if (dto.getIdFoto() != null) {
                Optional<Foto> foto = fotoService.getFotoById(dto.getIdFoto());
                if (!foto.isPresent()) {
                    veiculo.setFoto(null);
                } else {
                    veiculo.setFoto(foto.get());
                }
            }

        }
        return veiculo;
    }

}
