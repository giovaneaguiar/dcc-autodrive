package br.ufjf.autodriveapi.service;

import br.ufjf.autodriveapi.exception.RegraNegocioException;
import br.ufjf.autodriveapi.model.repository.VeiculoRepository;
import br.ufjf.autodriveapi.model.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class VeiculoService {
    private VeiculoRepository repository;

    public VeiculoService(VeiculoRepository veiculo) {
        this.repository = veiculo;
    }

    public List<Veiculo> getVeiculo() {
        return repository.findAll();
    }

    public Optional<Veiculo> getVeiculoById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Veiculo salvar(Veiculo veiculo) {
        validar(veiculo);
        return repository.save(veiculo);
    }

    @Transactional
    public void excluir(Veiculo veiculo) {
        Objects.requireNonNull(veiculo.getId());
        repository.delete(veiculo);
    }

    public void validar(Veiculo veiculo) {
        if (veiculo.getModelo() == null || veiculo.getModelo().trim().isEmpty()) {
            throw new RegraNegocioException("O modelo do veículo é obrigatório.");
        }

        if (veiculo.getPlaca() == null || veiculo.getPlaca().trim().isEmpty()) {
            throw new RegraNegocioException("A placa do veículo é obrigatória.");
        }

        if (veiculo.getCor() == null || veiculo.getCor().trim().isEmpty()) {
            throw new RegraNegocioException("A cor do veículo é obrigatória.");
        }

        if (veiculo.getAno() == null || veiculo.getAno().trim().isEmpty()) {
            throw new RegraNegocioException("O ano do veículo é obrigatório.");
        }

        if (veiculo.getVersao() == null || veiculo.getVersao().trim().isEmpty()) {
            throw new RegraNegocioException("A versão do veículo é obrigatório.");
        }

        if (veiculo.getPreco() == null || veiculo.getPreco() <= 0) {
            throw new RegraNegocioException("O preço do veículo deve ser maior que zero.");
        }

        if (veiculo.getCondicao() == null || veiculo.getCondicao().trim().isEmpty()) {
            throw new RegraNegocioException("A condição do veículo é obrigatória.");
        }

        if (veiculo.getEmpresa() == null || veiculo.getEmpresa().getId() == null) {
            throw new RegraNegocioException("A empresa do veículo é obrigatória.");
        }

        if (veiculo.getCategoria() == null || veiculo.getCategoria().getId() == null) {
            throw new RegraNegocioException("A categoria do veículo é obrigatória.");
        }

        if (veiculo.getMarca() == null || veiculo.getMarca().getId() == null) {
            throw new RegraNegocioException("A marca do veículo é obrigatória.");
        }

        if (veiculo.getTipo() == null || veiculo.getTipo().getId() == null) {
            throw new RegraNegocioException("O veículo deve ter um tipo.");
        }

        if (veiculo.getOpcional() == null || veiculo.getOpcional().getId() == null) {
            throw new RegraNegocioException("O veículo deve ter um opcional.");
        }

        if (veiculo.getFoto() == null || veiculo.getFoto().getId() == null) {
            throw new RegraNegocioException("Pelo menos uma foto do veículo é obrigatória.");
        }
    }

}
