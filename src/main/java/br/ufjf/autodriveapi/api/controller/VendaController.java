package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.VendaDTO;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.model.entity.Veiculo;
import br.ufjf.autodriveapi.model.entity.Venda;
import br.ufjf.autodriveapi.service.UsuarioService;
import br.ufjf.autodriveapi.service.VeiculoService;
import br.ufjf.autodriveapi.service.VendaService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/vendas")
@CrossOrigin

public class VendaController {

    private final VendaService service;
    private final UsuarioService usuarioService;
    private final VeiculoService veiculoService;

    public VendaController(VendaService service, UsuarioService usuarioService, VeiculoService veiculoService) {
            this.service = service;
        this.usuarioService = usuarioService;
        this.veiculoService = veiculoService;
    }

        @GetMapping()
        public ResponseEntity get() {
            List<Venda> vendas = service.getVenda();
            return ResponseEntity.ok(vendas.stream().map(VendaDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Venda> venda = service.getVendaById(id);
            if(!venda.isPresent()) {
                return new ResponseEntity("Vendas não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(venda.map(VendaDTO::create));
        }

        @PostMapping
        public ResponseEntity post(@RequestBody VendaDTO dto) {
            try {
                Venda venda = converter(dto);
                venda = service.salvar(venda);
                return new ResponseEntity(venda, HttpStatus.CREATED);
            }catch (Exception e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Venda converter(VendaDTO dto){
//            ModelMapper modelMapper = new ModelMapper();
//            Venda venda = modelMapper.map(dto, Venda.class);
            Venda venda = new Venda();

            venda.setDataVenda(dto.getDataVenda());
            venda.setValorFinal(dto.getValorFinal());
            venda.setConcluido(dto.getConcluido());
            venda.setStatus(dto.getStatus());

            if(dto.getIdUsuario() != null){
                Optional<Usuario> usuario = usuarioService.getUsuarioById(dto.getIdUsuario());
                if(!usuario.isPresent()){
                    venda.setUsuario(null);
                }else{
                    venda.setUsuario(usuario.get());
                }

            }

            if(dto.getIdVeiculo() != null){
                Optional<Veiculo> veiculo = veiculoService.getVeiculoById(dto.getIdVeiculo());
                if(!veiculo.isPresent()){
                    venda.setVeiculo(null);
                }else{
                    venda.setVeiculo(veiculo.get());
                }
            }
            return venda;
        }

}
