package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
