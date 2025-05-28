package com.gs.safealert.repository;

import com.gs.safealert.model.Ocorrencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    List<Ocorrencia> findByStatus(String status);

    List<Ocorrencia> findByPostagemId(Long postagemId);
}
