package com.tecsacadas.tecsacadasmanager.data.db.customer;

import com.tecsacadas.tecsacadasmanager.core.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final CustomerDao customerDao;

    public Optional<Customer> findByCpf(Long cpf) {
        return customerDao.findByCpf(cpf);
    }

    public Optional<Customer> findById(Long id) {
        return customerDao.findById(id);
    }

    public Customer save(Customer customer) {
        return customerDao.save(customer);
    }

    public void deleteById(Long id) {
        customerDao.deleteById(id);
    }

    public List<Customer> findAll() {
        return customerDao.findAll();
    }
}
