package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.FavoritoDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Favorito;

import br.ufjf.autodriveapi.model.entity.Veiculo;
import br.ufjf.autodriveapi.service.VeiculoService;
import br.ufjf.autodriveapi.service.UsuarioService;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.service.FavoritoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/favoritos")
@CrossOrigin

public class FavoritoController {

        private final FavoritoService service;
        private final UsuarioService usuarioService;
        private final VeiculoService veiculoService;


        public FavoritoController(FavoritoService service, UsuarioService usuarioService, VeiculoService veiculoService) {

            this.service = service;
            this.usuarioService = usuarioService;
            this.veiculoService = veiculoService;
        }

        @GetMapping()
        public ResponseEntity get() {
            List<Favorito> favoritos = service.getFavorito();
            return ResponseEntity.ok(favoritos.stream().map(FavoritoDTO::create).collect(Collectors.toList()));
        }
        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Favorito> favoritos = service.getFavoritoById(id);
            if(!favoritos.isPresent()) {
                return new ResponseEntity("Categoria não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(favoritos.map(FavoritoDTO::create));
        }

        @PostMapping()
        public ResponseEntity post(@RequestBody FavoritoDTO dto) {
            try {
                Favorito favorito = converter(dto);
                favorito = service.salvar(favorito);
                return new ResponseEntity(favorito, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

    @PutMapping("{id}")
    public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody FavoritoDTO dto) {
        if (!service.getFavoritoById(id).isPresent()) {
            return new ResponseEntity("Favorito não encontrado", HttpStatus.NOT_FOUND);
        }
        try {
            Favorito favorito = converter(dto);
            favorito.setId(id);
            service.salvar(favorito);
            return ResponseEntity.ok(favorito);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

        public Favorito converter(FavoritoDTO dto) {
//            ModelMapper modelMapper = new ModelMapper();
//            Favorito favorito = modelMapper.map(dto, Favorito.class);

            Favorito favorito = new Favorito();
            favorito.setDataFavorito(dto.getDataFavorito());
            favorito.setDescricao(dto.getDescricao());

            if(dto.getIdUsuario() != null){
                Optional<Usuario> usuario = usuarioService.getUsuarioById(dto.getIdUsuario());
                if(!usuario.isPresent()){
                    favorito.setUsuario(null);
                }else{
                    favorito.setUsuario(usuario.get());
                }

            }

            if(dto.getIdVeiculo() != null){
                Optional<Veiculo> veiculo = veiculoService.getVeiculoById(dto.getIdVeiculo());
                if(!veiculo.isPresent()){
                    favorito.setVeiculo(null);
                }else{
                    favorito.setVeiculo(veiculo.get());
                }
            }
            return favorito;
        }
}
