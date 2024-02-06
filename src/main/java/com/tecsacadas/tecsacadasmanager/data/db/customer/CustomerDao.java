package com.tecsacadas.tecsacadasmanager.data.db.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface CustomerDao extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCpf(Long cpf);
}
