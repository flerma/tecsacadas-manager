package com.tecsacadas.tecsacadasmanager.data.db.user;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.core.user.User;
import com.tecsacadas.tecsacadasmanager.data.db.group.GroupEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tecsacadas.tecsacadasmanager.data.db.user.UserEntity.toEntity;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserDao userDao;

    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login).map(UserEntity::toDomain);
    }

    @Query("SELECT u FROM UserEntity u JOIN u.groups g WHERE g.id = :groupId")
    public List<User> findByGroupId(@Param("groupId") Long groupId) {
        return userDao.findByGroupId(groupId).stream().map(UserEntity::toDomain).toList();
    }

    @Query("SELECT u.mainGroup FROM UserEntity u JOIN u.mainGroup g WHERE u.id = :userId")
    public Group findMainGroupById(@Param("userId") Long userId) {
        return userDao.findMainGroupById(userId).toDomain();
    }

    @Query("SELECT u.groups FROM UserEntity u JOIN u.groups g WHERE u.id = :userId")
    public List<Group> findGroupsByUserId(@Param("userId") Long userId) {
        return userDao.findGroupsByUserId(userId).stream()
                .map(GroupEntity::toDomain)
                .toList();
    }

    public List<User> findAll() {
        return userDao.findAll().stream().map(UserEntity::toDomain).toList();
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id).map(UserEntity::toDomain);
    }

    public User save(User user) {
        return userDao.save(toEntity(user)).toDomain();
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
