package com.tecsacadas.tecsacadasmanager.data.db.group;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface GroupDao extends JpaRepository<GroupEntity, Long> {

    Optional<GroupEntity> findByName(String nome);

    Optional<GroupEntity> findById(Long id);
}
