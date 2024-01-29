package com.tecsacadas.tecsacadasmanager.repository;

import com.tecsacadas.tecsacadasmanager.domain.model.Group;
import com.tecsacadas.tecsacadasmanager.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);

    @Query("SELECT u FROM User u JOIN u.groups g WHERE g.id = :groupId")
    Set<User> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT u.mainGroup FROM User u JOIN u.mainGroup g WHERE u.id = :userId")
    Group findMainGroupById(@Param("userId") Long userId);

    @Query("SELECT u.groups FROM User u JOIN u.groups g WHERE u.id = :userId")
    Set<Group> findGroupsByUserId(@Param("userId") Long userId);
}
