package com.tecsacadas.tecsacadasmanager.data.db.customer;

import com.tecsacadas.tecsacadasmanager.core.customer.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.tecsacadas.tecsacadasmanager.data.db.customer.CustomerEntity.toDomain;

@Repository
@RequiredArgsConstructor
class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerDao customerDao;

    public Optional<Customer> findByCpf(Long cpf) {
        return customerDao.findByCpf(cpf).map(CustomerEntity::toDomain);
    }

    public Optional<Customer> findById(Long id) {
        return customerDao.findById(id).map(CustomerEntity::toDomain);
    }

    public Customer save(Customer customer) {
        return toDomain(customerDao.save(CustomerEntity.toEntity(customer)));
    }

    public void deleteById(Long id) {
        customerDao.deleteById(id);
    }

    public List<Customer> findAll() {
        return customerDao.findAll().stream().map(CustomerEntity::toDomain).toList();
    }
}
