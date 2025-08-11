package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.UsuarioAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioAdminRepository extends JpaRepository<UsuarioAdmin, Long> {
    Optional<UsuarioAdmin> findByLogin(String login);
}