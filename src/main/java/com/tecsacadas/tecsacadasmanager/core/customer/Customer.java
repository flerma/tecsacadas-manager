package com.tecsacadas.tecsacadasmanager.core.customer;

import com.tecsacadas.tecsacadasmanager.core.address.StateEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

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

    private StateEnum state;

    private Long cep;
}
