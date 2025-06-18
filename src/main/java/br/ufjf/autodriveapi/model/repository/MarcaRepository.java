package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Marca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
}
