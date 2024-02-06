package com.tecsacadas.tecsacadasmanager.core.customer;

import com.tecsacadas.tecsacadasmanager.data.db.customer.CustomerEntity;
import com.tecsacadas.tecsacadasmanager.data.db.customer.CustomerRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.presentation.customer.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }

    public Optional<CustomerDto> findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerEntity::toDto);
    }

    public CustomerDto create(CustomerDto customer) {
        Optional<CustomerEntity> existingCustomer = customerRepository.findByCpf(customer.getCpf());
        if (existingCustomer.isPresent()) {
            throw new BusinessException("Cliente já existe com o cpf: " + customer.getCpf());
        } else {
            return customerRepository.save(customer.toModel()).toDto();
        }
    }

    public CustomerDto update(Long id, CustomerDto updatedCustomer) {
        Optional<CustomerEntity> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer.toModel()).toDto();
        } else {
            throw new BusinessException("Cliente não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        Optional<CustomerEntity> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new BusinessException("Cliente não encontrado com o ID: " + id);
        }
    }
}
