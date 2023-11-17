package com.tecsacadas.tecsacadasmanager.repository;

import com.tecsacadas.tecsacadasmanager.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

    @Query("SELECT u FROM Usuario u JOIN u.grupos g WHERE g.id = :grupoId")
    Set<Usuario> findByGrupoId(@Param("grupoId") Long grupoId);

}
