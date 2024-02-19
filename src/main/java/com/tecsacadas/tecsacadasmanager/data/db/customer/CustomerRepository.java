package com.tecsacadas.tecsacadasmanager.data.db.customer;

import com.tecsacadas.tecsacadasmanager.core.customer.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> findByCpf(Long cpf);

    Optional<Customer> findById(Long id);

    Customer save(Customer customer);

    void deleteById(Long id);

    List<Customer> findAll();
}
