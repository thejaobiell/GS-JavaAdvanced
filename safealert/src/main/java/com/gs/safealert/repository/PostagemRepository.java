package com.gs.safealert.repository;

import com.gs.safealert.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    @Query(value = "SELECT * FROM postagens WHERE usuario_id = :usuarioId", nativeQuery = true)
    List<Postagem> findByUsuarioId(Long usuarioId);

    @Query(value = "SELECT * FROM postagens WHERE evento_id = :eventoId", nativeQuery = true)
    List<Postagem> findByEventoId(Long eventoId);

    @Query(value = "SELECT * FROM postagens WHERE localidade_id = :localidadeId", nativeQuery = true)
    List<Postagem> findByLocalidadeId(Long localidadeId);
}
