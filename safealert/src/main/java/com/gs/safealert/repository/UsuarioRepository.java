package com.gs.safealert.repository;

import com.gs.safealert.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    
    @Query(value = "SELECT * FROM usuarios WHERE LOWER(endereco) LIKE LOWER('%' || :endereco || '%')", nativeQuery = true)
    List<Usuario> findByEndereco(@Param("endereco") String endereco);
}
