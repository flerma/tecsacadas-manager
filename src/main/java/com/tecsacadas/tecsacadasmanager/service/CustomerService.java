package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.Customer;
import com.tecsacadas.tecsacadasmanager.dto.CustomerDto;
import com.tecsacadas.tecsacadasmanager.exception.BusinessException;
import com.tecsacadas.tecsacadasmanager.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public Optional<CustomerDto> findById(Long id) {
        return customerRepository.findById(id)
                .map(Customer::toDto);
    }

    public CustomerDto create(CustomerDto customer) {
        Optional<Customer> existingCustomer = customerRepository.findByCpf(customer.getCpf());
        if (existingCustomer.isPresent()) {
            throw new BusinessException("Cliente já existe com o cpf: " + customer.getCpf());
        } else {
            return customerRepository.save(customer.toModel()).toDto();
        }
    }

    public CustomerDto update(Long id, CustomerDto updatedCustomer) {
        Optional<Customer> existingCustomer = customerRepository.findById(id);
        if (existingCustomer.isPresent()) {
            updatedCustomer.setId(id);
            return customerRepository.save(updatedCustomer.toModel()).toDto();
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
