package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.domain.model.Group;
import com.tecsacadas.tecsacadasmanager.domain.model.User;
import com.tecsacadas.tecsacadasmanager.dto.UserDto;
import com.tecsacadas.tecsacadasmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/grupo/{groupId}")
    public ResponseEntity<Set<User>> findByGroupId(@PathVariable Long groupId) {
        Set<User> users = userService.findByGroupId(groupId);
        return ResponseEntity.ok(users);
    }

    @GetMapping("/grupo-principal/{userId}")
    public ResponseEntity<Group> findMainGroupByUserId(@PathVariable Long userId) {
        Group group = userService.findMainGroupById(userId);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/lista-grupos/{userId}")
    public ResponseEntity<Set<Group>> findGroupsById(@PathVariable Long userId) {
        Set<Group> groups = userService.findGroupsById(userId);
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id) {
        Optional<UserDto> users = userService.findById(id);
        return users.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto user) {
        UserDto newUser = userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable Long id, @Valid @RequestBody UserDto updatedUser) {
        UserDto user = userService.update(id, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }
}
