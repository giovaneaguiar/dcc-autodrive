package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.VendaRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VendaService {
    private VendaRepository repository;

    public VendaService(VendaRepository venda) {
        this.repository = venda;
    }

    public List<Venda> getVenda() {
        return repository.findAll();
    }

    public Optional<Venda> getVendaById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Venda salvar(Venda venda) {
        validar(venda);
        return repository.save(venda);
    }

    @Transactional
    public void excluir(Venda venda) {
        Objects.requireNonNull(venda.getId());
        repository.delete(venda);
    }

    public void validar(Venda venda) {
        if (venda.getDataVenda() == null) {
            throw new RegraNegocioException("A data da venda é obrigatória.");
        }

        if (venda.getValorFinal() <= 0) {
            throw new RegraNegocioException("O valor final da venda deve ser maior que zero.");
        }

        if (venda.getConcluido() != true || venda.getConcluido() != false) {
            throw new RegraNegocioException("O atatus de concluído deve ser verdadeiro ou falso.");
        }

        if (venda.getDescricao() == null || venda.getDescricao().trim().isEmpty()) {
            throw new RegraNegocioException("A descrição do veículo é obrigatório.");
        }

        if (venda.getStatus() == null || venda.getStatus().trim().isEmpty()) {
            throw new RegraNegocioException("O status da venda é obrigatório.");
        }

        if (venda.getUsuario() == null || venda.getUsuario().getId() == null) {
            throw new RegraNegocioException("O usuário responsável pela venda é obrigatório.");
        }

        if (venda.getVeiculo() == null || venda.getVeiculo().getId() == null) {
            throw new RegraNegocioException("O veículo associado à venda é obrigatório.");
        }
    }

}
