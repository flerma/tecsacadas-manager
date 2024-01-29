package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.Group;
import com.tecsacadas.tecsacadasmanager.domain.model.User;
import com.tecsacadas.tecsacadasmanager.dto.UserDto;
import com.tecsacadas.tecsacadasmanager.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Set<User> findByGroupId(Long grupoId) {
        return userRepository.findByGroupId(grupoId);
    }

    public Group findMainGroupById(Long userId) {
        return userRepository.findMainGroupById(userId);
    }

    public Set<Group> findGroupsById(Long userId) {
        return userRepository.findGroupsByUserId(userId);
    }

    public Optional<UserDto> findById(Long id) {
        return userRepository.findById(id)
                .map(User::toDto);
    }

    public Optional<UserDto> buscarPorLogin(String login) {
        return userRepository.findByLogin(login)
                .map(User::toDto);
    }

    public UserDto create(UserDto user) {
        Optional<User> existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser.isPresent()) {
            throw new BusinessException("Usuário já existe com o login: " + user.getLogin());
        } else {
            return userRepository.save(user.toModel()).toDto();
        }
    }

    public UserDto update(Long id, UserDto updatedUser) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            updatedUser.setId(id);
            return userRepository.save(updatedUser.toModel()).toDto();
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
