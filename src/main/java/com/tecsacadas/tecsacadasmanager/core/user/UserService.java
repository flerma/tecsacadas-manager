package com.tecsacadas.tecsacadasmanager.core.user;

import com.tecsacadas.tecsacadasmanager.data.db.user.UserRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.presentation.group.GroupDto;
import com.tecsacadas.tecsacadasmanager.presentation.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tecsacadas.tecsacadasmanager.presentation.group.GroupDto.toDto;
import static com.tecsacadas.tecsacadasmanager.presentation.user.UserDto.toDto;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(UserDto::toDto).toList();
    }

    public List<UserDto> findByGroupId(Long grupoId) {
        return userRepository.findByGroupId(grupoId).stream().map(UserDto::toDto).toList();
    }

    public GroupDto findMainGroupById(Long userId) {
        return toDto(userRepository.findMainGroupById(userId));
    }

    public List<GroupDto> findGroupsById(Long userId) {
        return userRepository.findGroupsByUserId(userId).stream().map(GroupDto::toDto).toList();
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(UserDto::toDto);
    }

    public Optional<UserDto> buscarPorLogin(String login) {
        return userRepository.findByLogin(login)
                .map(UserDto::toDto);
    }

    public UserDto create(UserDto user) {
        Optional<User> existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser.isPresent()) {
            throw new BusinessException("Usuário já existe com o login: " + user.getLogin());
        } else {
            return toDto(userRepository.save(user.toDomain()));
        }
    }

    public UserDto update(Long id, UserDto updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id);
            return toDto(userRepository.save(updatedUser.toDomain()));
        } else {
            throw new BusinessException("Usuário não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new BusinessException("Usuário não encontrado com o ID: " + id);
        }
    }
}
