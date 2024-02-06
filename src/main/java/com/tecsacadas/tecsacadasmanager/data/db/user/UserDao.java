package com.tecsacadas.tecsacadasmanager.data.db.user;

import com.tecsacadas.tecsacadasmanager.data.db.group.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

interface UserDao extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByLogin(String login);

    @Query("SELECT u FROM UserEntity u JOIN u.groups g WHERE g.id = :groupId")
    List<UserEntity> findByGroupId(@Param("groupId") Long groupId);

    @Query("SELECT u.mainGroup FROM UserEntity u JOIN u.mainGroup g WHERE u.id = :userId")
    GroupEntity findMainGroupById(@Param("userId") Long userId);

    @Query("SELECT u.groups FROM UserEntity u JOIN u.groups g WHERE u.id = :userId")
    List<GroupEntity> findGroupsByUserId(@Param("userId") Long userId);
}
