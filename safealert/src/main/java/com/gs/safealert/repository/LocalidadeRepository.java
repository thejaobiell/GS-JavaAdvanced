package com.gs.safealert.repository;

import com.gs.safealert.model.Localidade;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocalidadeRepository extends JpaRepository<Localidade, Long> {
    @Query("SELECT l FROM Localidade l WHERE LOWER(l.bairro) LIKE LOWER(CONCAT('%', :bairro, '%'))")
    List<Localidade> buscarPorBairro(@Param("bairro") String bairro);
}
