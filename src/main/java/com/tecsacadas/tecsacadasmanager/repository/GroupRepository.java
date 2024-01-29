package com.tecsacadas.tecsacadasmanager.repository;

import com.tecsacadas.tecsacadasmanager.domain.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String nome);

    Optional<Group> findById(Long id);
}
