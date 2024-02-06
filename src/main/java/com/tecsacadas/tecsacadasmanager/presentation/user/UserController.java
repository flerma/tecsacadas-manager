package com.tecsacadas.tecsacadasmanager.presentation.user;

import com.tecsacadas.tecsacadasmanager.core.user.UserService;
import com.tecsacadas.tecsacadasmanager.presentation.group.GroupDto;
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

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/grupo/{groupId}")
    public ResponseEntity<List<UserDto>> findByGroupId(@PathVariable Long groupId) {
        return ResponseEntity.ok(userService.findByGroupId(groupId));
    }

    @GetMapping("/grupo-principal/{userId}")
    public ResponseEntity<GroupDto> findMainGroupByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findMainGroupById(userId));
    }

    @GetMapping("/lista-grupos/{userId}")
    public ResponseEntity<List<GroupDto>> findGroupsById(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.findGroupsById(userId));
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
