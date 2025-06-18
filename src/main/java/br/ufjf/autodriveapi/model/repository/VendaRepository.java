package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Venda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
