package com.tecsacadas.tecsacadasmanager.data.db.group;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
class GroupRepositoryImpl implements GroupRepository {

    private final GroupDao groupDao;

    @Override
    public Optional<Group> findByName(String nome) {
        return groupDao.findByName(nome).map(Group::toDomain);
    }

    @Override
    public Optional<Group> findById(Long id) {
        return groupDao.findById(id).map(GroupEntity::toDomain);
    }

    @Override
    public Group save(Group group) {
        return groupDao.save(GroupEntity.toEntity(group)).toDomain();
    }

    @Override
    public void deleteById(Long id) {
        groupDao.deleteById(id);
    }

    @Override
    public List<Group> findAll() {
        return groupDao.findAll().stream().map(GroupEntity::toDomain).toList();
    }
}
