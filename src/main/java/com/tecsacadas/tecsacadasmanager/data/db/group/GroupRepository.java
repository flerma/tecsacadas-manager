package com.tecsacadas.tecsacadasmanager.data.db.group;

import com.tecsacadas.tecsacadasmanager.core.group.Group;

import java.util.List;
import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findByName(String nome);

    Optional<Group> findById(Long id);

    Group save(Group group);

    void deleteById(Long id);

    List<Group> findAll();
}
