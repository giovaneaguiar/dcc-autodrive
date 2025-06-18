package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
}
