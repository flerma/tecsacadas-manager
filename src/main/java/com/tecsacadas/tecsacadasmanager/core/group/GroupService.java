package com.tecsacadas.tecsacadasmanager.core.group;

import com.tecsacadas.tecsacadasmanager.data.db.group.GroupRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.presentation.group.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tecsacadas.tecsacadasmanager.presentation.group.GroupDto.toDto;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public List<GroupDto> findlAll() {
        return groupRepository.findAll().stream().map(GroupDto::toDto).toList();
    }

    public GroupDto findById(Long id) {
        return groupRepository.findById(id).map(GroupDto::toDto)
                .orElseThrow(() -> new BusinessException("Grupo não encontrado com o ID: " + id));
    }

    public GroupDto create(GroupDto group) {
        Optional<Group> existingGroup = groupRepository.findByName(group.getName());
        if (existingGroup.isPresent()) {
            throw new BusinessException("Grupo já existe com o nome: " + group.getName());
        } else {
            return toDto(groupRepository.save(group.toDomain()));
        }
    }

    public GroupDto update(Long id, GroupDto updatedGroup) {
        Optional<Group> existingGroup = groupRepository.findById(id);
        if (existingGroup.isPresent()) {
            updatedGroup.setId(id);
            return toDto(groupRepository.save(updatedGroup.toDomain()));
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
