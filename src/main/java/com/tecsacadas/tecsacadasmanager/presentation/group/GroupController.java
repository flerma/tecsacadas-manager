package com.tecsacadas.tecsacadasmanager.presentation.group;

import com.tecsacadas.tecsacadasmanager.core.group.GroupService;
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

@RestController
@RequestMapping("/grupos")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<GroupDto> findAll() {
        return groupService.findlAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public GroupDto findById(@PathVariable Long id) {
        return groupService.findById(id);
    }

    @PostMapping
    public ResponseEntity<GroupDto> create(@Valid @RequestBody GroupDto group) {
        GroupDto newGroup = groupService.create(group);
        return ResponseEntity.status(HttpStatus.CREATED).body(newGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> update(@PathVariable Long id, @Valid @RequestBody GroupDto updatedGroup) {
        GroupDto group = groupService.update(id, updatedGroup);
        return ResponseEntity.ok(group);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        groupService.delete(id);
    }
}
