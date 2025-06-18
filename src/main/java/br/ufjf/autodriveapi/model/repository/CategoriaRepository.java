package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
