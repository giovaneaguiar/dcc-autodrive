package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    List<Veiculo> findByEmpresa(Optional<Empresa> empresa);
}
