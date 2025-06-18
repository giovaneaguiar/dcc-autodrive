package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
}
