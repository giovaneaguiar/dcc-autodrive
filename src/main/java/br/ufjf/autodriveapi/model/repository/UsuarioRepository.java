package br.ufjf.autodriveapi.model.repository;

import br.ufjf.autodriveapi.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
