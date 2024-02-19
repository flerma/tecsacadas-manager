package com.tecsacadas.tecsacadasmanager.data.db.user;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.core.user.User;
import com.tecsacadas.tecsacadasmanager.data.db.group.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.tecsacadas.tecsacadasmanager.data.db.user.UserEntity.toEntity;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    private final UserDao userDao;

    @Override
    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login).map(UserEntity::toDomain);
    }

    @Override
    public List<User> findByGroupId(@Param("groupId") Long groupId) {
        return userDao.findByGroupId(groupId).stream().map(UserEntity::toDomain).toList();
    }

    @Override
    public Group findMainGroupById(@Param("userId") Long userId) {
        return userDao.findMainGroupById(userId).toDomain();
    }

    @Override
    public List<Group> findGroupsByUserId(@Param("userId") Long userId) {
        return userDao.findGroupsByUserId(userId).stream()
                .map(GroupEntity::toDomain)
                .toList();
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll().stream().map(UserEntity::toDomain).toList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id).map(UserEntity::toDomain);
    }

    @Override
    public User save(User user) {
        return userDao.save(toEntity(user)).toDomain();
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
