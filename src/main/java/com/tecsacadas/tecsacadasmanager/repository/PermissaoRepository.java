package com.tecsacadas.tecsacadasmanager.repository;

import com.tecsacadas.tecsacadasmanager.domain.model.Grupo;
import com.tecsacadas.tecsacadasmanager.domain.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}
