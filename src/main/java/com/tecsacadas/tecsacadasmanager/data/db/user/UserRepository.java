package com.tecsacadas.tecsacadasmanager.data.db.user;

import com.tecsacadas.tecsacadasmanager.core.group.Group;
import com.tecsacadas.tecsacadasmanager.core.user.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByLogin(String login);

    List<User> findByGroupId(@Param("groupId") Long groupId);

    Group findMainGroupById(@Param("userId") Long userId);

    List<Group> findGroupsByUserId(@Param("userId") Long userId);

    List<User> findAll();

    Optional<User> findById(Long id);

    User save(User user);

    void deleteById(Long id);
}
