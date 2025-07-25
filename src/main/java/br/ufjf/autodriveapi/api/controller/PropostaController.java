package br.ufjf.autodriveapi.api.controller;

import br.ufjf.autodriveapi.api.dto.AnuncioDTO;
import br.ufjf.autodriveapi.api.dto.NotificacaoDTO;
import br.ufjf.autodriveapi.api.dto.PropostaDTO;
import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.entity.Anuncio;
import br.ufjf.autodriveapi.model.entity.Notificacao;
import br.ufjf.autodriveapi.model.entity.Proposta;
import br.ufjf.autodriveapi.model.entity.Usuario;
import br.ufjf.autodriveapi.service.PropostaService;
import br.ufjf.autodriveapi.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/propostas")
@CrossOrigin

public class PropostaController {

    private final PropostaService service;
    private final UsuarioService usuarioService;

    public PropostaController(PropostaService service, UsuarioService usuarioService) {
            this.service = service;
        this.usuarioService = usuarioService;
    }

        @GetMapping()
        public ResponseEntity get() {
            List<Proposta> propostas = service.getProposta();
            return ResponseEntity.ok(propostas.stream().map(PropostaDTO::create).collect(Collectors.toList()));
        }

        @GetMapping("/{id}")
        public ResponseEntity get(@PathVariable("id") Long id) {
            Optional<Proposta> proposta = service.getPropostaById(id);
            if(!proposta.isPresent()) {
                return new ResponseEntity("Proposta não encontrada", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(proposta.map(PropostaDTO::create));
        }

        @PostMapping
        public ResponseEntity post(@RequestBody PropostaDTO dto) {
            try {
                Proposta proposta = converter(dto);
                proposta = service.salvar(proposta);
                return new ResponseEntity(proposta, HttpStatus.CREATED);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }

        }

        @PutMapping("{id}")
        public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody PropostaDTO dto) {
            if (!service.getPropostaById(id).isPresent()) {
                return new ResponseEntity("Proposta não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                Proposta proposta = converter(dto);
                proposta.setId(id);
                service.salvar(proposta);
                return ResponseEntity.ok(proposta);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        @DeleteMapping("{id}")
        public ResponseEntity excluir(@PathVariable("id") Long id) {
            Optional<Proposta> proposta = service.getPropostaById(id);
            if (!proposta.isPresent()) {
                return new ResponseEntity("Proposta não encontrada", HttpStatus.NOT_FOUND);
            }
            try {
                service.excluir(proposta.get());
                return new ResponseEntity(HttpStatus.NO_CONTENT);
            } catch (RegraNegocioException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }

        public Proposta converter(PropostaDTO dto) {
            ModelMapper modelMapper = new ModelMapper();
            Proposta proposta = modelMapper.map(dto, Proposta.class);

            if(dto.getIdUsuario() != null) {
                Optional<Usuario> usuario = usuarioService.getUsuarioById(dto.getIdUsuario());
                if(!usuario.isPresent()) {
                    proposta.setUsuario(null);
                } else {
                    proposta.setUsuario(usuario.get());
                }
            }

            return proposta;
        }
}
