package com.tecsacadas.tecsacadasmanager.data.db.customer;

import com.tecsacadas.tecsacadasmanager.core.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CustomerDao extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpf(Long cpf);
}
