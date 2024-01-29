package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.Group;
import com.tecsacadas.tecsacadasmanager.domain.model.User;
import com.tecsacadas.tecsacadasmanager.dto.GroupDto;
import com.tecsacadas.tecsacadasmanager.dto.UserDto;
import com.tecsacadas.tecsacadasmanager.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<Group> findlAll() {
        return groupRepository.findAll();
    }

    public Group findById(Long id) {
        return groupRepository.findById(id).orElse(null);
    }

    public GroupDto create(GroupDto group) {
        Optional<Group> existingGroup = groupRepository.findByName(group.getName());
        if (existingGroup.isPresent()) {
            throw new BusinessException("Grupo já existe com o nome: " + group.getName());
        } else {
            return groupRepository.save(group.toModel()).toDto();
        }
    }

    public GroupDto update(Long id, GroupDto updatedGroup) {
        Optional<Group> existingGroup = groupRepository.findById(id);
        if (existingGroup.isPresent()) {
            updatedGroup.setId(id);
            return groupRepository.save(updatedGroup.toModel()).toDto();
        } else {
            throw new BusinessException("Grupo não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Group> existingGroup = groupRepository.findById(id);
        if (existingGroup.isPresent()) {
            groupRepository.deleteById(id);
        } else {
            throw new BusinessException("Grupo não encontrado com o ID: " + id);
        }
    }
}
