package com.tecsacadas.tecsacadasmanager.data.db.customer;


import com.tecsacadas.tecsacadasmanager.core.address.StateEnum;
import com.tecsacadas.tecsacadasmanager.presentation.customer.CustomerDto;
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

    public CustomerDto toDto() {
        return CustomerDto.builder()
                .id(id)
                .name(name)
                .cpf(cpf)
                .rg(rg)
                .email(email)
                .phone(phone)
                .address(address)
                .number(number)
                .neighborhood(neighborhood)
                .city(city)
                .state(state)
                .cep(cep)
                .build();
    }
}
