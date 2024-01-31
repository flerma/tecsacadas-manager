package com.tecsacadas.tecsacadasmanager.data.db.user;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.core.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserDao userDao;

    public Optional<User> findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Query("SELECT u FROM User u JOIN u.groups g WHERE g.id = :groupId")
    public Set<User> findByGroupId(@Param("groupId") Long groupId) {
        return userDao.findByGroupId(groupId);
    }

    @Query("SELECT u.mainGroup FROM User u JOIN u.mainGroup g WHERE u.id = :userId")
    public Group findMainGroupById(@Param("userId") Long userId) {
        return userDao.findMainGroupById(userId);
    }

    @Query("SELECT u.groups FROM User u JOIN u.groups g WHERE u.id = :userId")
    public Set<Group> findGroupsByUserId(@Param("userId") Long userId) {
        return userDao.findGroupsByUserId(userId);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    public User save(User user) {
        return userDao.save(user);
    }

    public void deleteById(Long id) {
        userDao.deleteById(id);
    }
}
