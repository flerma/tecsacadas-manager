package com.tecsacadas.tecsacadasmanager.domain.model;


import com.tecsacadas.tecsacadasmanager.domain.enums.StateEnum;
import com.tecsacadas.tecsacadasmanager.dto.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "customer")
public class Customer {

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
