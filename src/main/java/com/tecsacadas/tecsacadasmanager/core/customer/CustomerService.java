package com.tecsacadas.tecsacadasmanager.core.customer;

import com.tecsacadas.tecsacadasmanager.data.db.customer.CustomerRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.presentation.customer.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.tecsacadas.tecsacadasmanager.presentation.customer.CustomerDto.toDomain;
import static com.tecsacadas.tecsacadasmanager.presentation.customer.CustomerDto.toDto;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<CustomerDto> findAll() {
        return customerRepository.findAll().stream().map(CustomerDto::toDto).toList();
    }

    public Optional<CustomerDto> findById(Long id) {
        return customerRepository.findById(id)
                .map(CustomerDto::toDto);
    }

    public CustomerDto create(CustomerDto customer) {
        Optional<Customer> existingCustomer = customerRepository.findByCpf(customer.getCpf());
        if (existingCustomer.isPresent()) {
            throw new BusinessException("Cliente já existe com o cpf: " + customer.getCpf());
        } else {
            return toDto(customerRepository.save(toDomain(customer)));
        }
    }

    public CustomerDto update(Long id, CustomerDto updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            updatedCustomer.setId(id);
            return toDto(customerRepository.save(toDomain(updatedCustomer)));
        } else {
            throw new BusinessException("Cliente não encontrado com o ID: " + id);
        }
    }

    public void delete(Long id) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            customerRepository.deleteById(id);
        } else {
            throw new BusinessException("Cliente não encontrado com o ID: " + id);
        }
    }
}
