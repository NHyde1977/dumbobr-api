package br.com.dumbobr.api.repository;

import br.com.dumbobr.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}