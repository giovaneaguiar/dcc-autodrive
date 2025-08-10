package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Empresa;
import br.ufjf.autodriveapi.model.entity.Opcional;
import br.ufjf.autodriveapi.model.entity.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
