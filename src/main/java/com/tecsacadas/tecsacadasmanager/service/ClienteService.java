package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.Cliente;
import com.tecsacadas.tecsacadasmanager.dto.ClienteDto;
import com.tecsacadas.tecsacadasmanager.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    public Optional<ClienteDto> buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .map(Cliente::toDto);
    }

    public ClienteDto criar(ClienteDto cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findByCpf(cliente.getCpf());
        if (clienteExistente.isPresent()) {
            throw new BusinessException("Cliente já existe com o cpf: " + cliente.getCpf());
        } else {
            return clienteRepository.save(cliente.toModel()).toDto();
        }
    }

    public ClienteDto atualizar(Long id, ClienteDto clienteAtualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            clienteAtualizado.setId(id);
            return clienteRepository.save(clienteAtualizado.toModel()).toDto();
        } else {
            throw new BusinessException("Cliente não encontrado com o ID: " + id);
        }
    }

    public void excluir(Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new BusinessException("Cliente não encontrado com o ID: " + id);
        }
    }
}
