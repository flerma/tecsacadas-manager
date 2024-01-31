package com.tecsacadas.tecsacadasmanager.data.db.group;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GroupRepository {

    private final GroupDao groupDao;

    public Optional<Group> findByName(String nome) {
        return groupDao.findByName(nome);
    }

    public Optional<Group> findById(Long id) {
        return groupDao.findById(id);
    }

    public Group save(Group group) {
        return groupDao.save(group);
    }

    public void deleteById(Long id) {
        groupDao.deleteById(id);
    }

    public List<Group> findAll() {
        return groupDao.findAll();
    }
}
