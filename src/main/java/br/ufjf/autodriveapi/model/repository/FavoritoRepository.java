package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
}
