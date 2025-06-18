package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Foto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FotoRepository extends JpaRepository<Foto, Long> {
}
