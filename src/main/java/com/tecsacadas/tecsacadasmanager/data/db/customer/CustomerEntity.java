package com.tecsacadas.tecsacadasmanager.data.db.customer;


import com.tecsacadas.tecsacadasmanager.core.address.StateEnum;
import com.tecsacadas.tecsacadasmanager.core.customer.Customer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long cpf;

    private String rg;

    private String email;

    private Long phone;

    private String address;

    private String number;

    private String neighborhood;

    private String city;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    private Long cep;

    public static Customer toDomain(CustomerEntity customer) {
        return Customer.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .rg(customer.getRg())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .number(customer.getNumber())
                .neighborhood(customer.getNeighborhood())
                .city(customer.getCity())
                .state(customer.getState())
                .cep(customer.getCep())
                .build();
    }

    public static CustomerEntity toEntity(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .rg(customer.getRg())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .number(customer.getNumber())
                .neighborhood(customer.getNeighborhood())
                .city(customer.getCity())
                .state(customer.getState())
                .cep(customer.getCep())
                .build();
    }
}
