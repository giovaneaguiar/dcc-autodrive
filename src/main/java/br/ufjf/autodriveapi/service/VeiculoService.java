package br.ufjf.autodriveapi.service;

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

    public void validar(Veiculo veiculo) {}
}
