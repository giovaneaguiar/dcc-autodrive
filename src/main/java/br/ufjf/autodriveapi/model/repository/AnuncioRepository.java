package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.anuncio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioRepository  extends JpaRepository<anuncio, Long> {
}
