package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Tipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRepository extends JpaRepository<Tipo, Long> {
}
