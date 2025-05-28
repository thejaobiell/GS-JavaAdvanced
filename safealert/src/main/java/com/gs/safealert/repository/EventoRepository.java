package com.gs.safealert.repository;

import com.gs.safealert.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {

	@Query("SELECT e FROM Evento e WHERE LOWER(e.tipo) LIKE LOWER('%' || :tipo || '%')")
    List<Evento> findByTipo(String tipo);
}
