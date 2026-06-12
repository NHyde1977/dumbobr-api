package br.com.dumbobr.api.repository;

import br.com.dumbobr.api.model.ObjetoRastreado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ObjetoRastreadoRepository extends JpaRepository<ObjetoRastreado, Long> {

 List<ObjetoRastreado> findByUsuarioId(Long usuarioId);

}
