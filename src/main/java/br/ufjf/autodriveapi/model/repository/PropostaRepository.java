package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Proposta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropostaRepository extends JpaRepository<Proposta, Long> {
}
