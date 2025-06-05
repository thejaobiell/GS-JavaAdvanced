package com.gs.safealert.repository;

import com.gs.safealert.model.Postagem;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    Page<Postagem> findAll(Pageable pageable);

    @Query(value = """
            SELECT * FROM postagens
            WHERE LOWER(titulo) LIKE LOWER(CONCAT('%', :termo, '%'))
            OR LOWER(descricao) LIKE LOWER(CONCAT('%', :termo, '%'))
            """, countQuery = """
            SELECT COUNT(*) FROM postagens
            WHERE LOWER(titulo) LIKE LOWER(CONCAT('%', :termo, '%'))
            OR LOWER(descricao) LIKE LOWER(CONCAT('%', :termo, '%'))
            """, nativeQuery = true)
    Page<Postagem> buscarPorTituloOuDescricao(@Param("termo") String termo, Pageable pageable);

    @Query(value = "SELECT * FROM postagens WHERE usuario_id = :usuarioId", nativeQuery = true)
    List<Postagem> findByUsuarioId(Long usuarioId);

    @Query(value = "SELECT * FROM postagens WHERE evento_id = :eventoId", nativeQuery = true)
    List<Postagem> findByEventoId(Long eventoId);

    @Query(value = "SELECT * FROM postagens WHERE localidade_id = :localidadeId", nativeQuery = true)
    List<Postagem> findByLocalidadeId(Long localidadeId);
}
