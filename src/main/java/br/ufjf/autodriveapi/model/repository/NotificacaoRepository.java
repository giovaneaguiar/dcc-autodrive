package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Notificacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacaoRepository extends JpaRepository<Notificacao, Long> {
}
