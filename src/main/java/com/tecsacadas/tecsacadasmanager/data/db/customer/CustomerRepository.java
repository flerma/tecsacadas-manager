package com.tecsacadas.tecsacadasmanager.data.db.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepository {

    private final CustomerDao customerDao;

    public Optional<CustomerEntity> findByCpf(Long cpf) {
        return customerDao.findByCpf(cpf);
    }

    public Optional<CustomerEntity> findById(Long id) {
        return customerDao.findById(id);
    }

    public CustomerEntity save(CustomerEntity customer) {
        return customerDao.save(customer);
    }

    public void deleteById(Long id) {
        customerDao.deleteById(id);
    }

    public List<CustomerEntity> findAll() {
        return customerDao.findAll();
    }
}
