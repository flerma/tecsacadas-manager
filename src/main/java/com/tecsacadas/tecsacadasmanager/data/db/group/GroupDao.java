package com.tecsacadas.tecsacadasmanager.data.db.group;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface GroupDao extends JpaRepository<Group, Long> {

    Optional<Group> findByName(String nome);

    Optional<Group> findById(Long id);
}
