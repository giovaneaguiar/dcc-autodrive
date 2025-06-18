package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Financiamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanciamentoRepository extends JpaRepository<Financiamento, Long> {
}
