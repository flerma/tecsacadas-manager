package com.tecsacadas.tecsacadasmanager.presentation.customer;

import com.tecsacadas.tecsacadasmanager.core.address.StateEnum;
import com.tecsacadas.tecsacadasmanager.core.customer.Customer;
import com.tecsacadas.tecsacadasmanager.data.db.customer.CustomerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotNull(message = "CPF é obrigatório")
    private Long cpf;

    private String rg;

    @NotBlank(message = "Email é obrigatório")
    @Email
    private String email;

    @NotNull(message = "Telefone é obrigatório")
    private Long phone;

    @NotBlank(message = "Endereço é obrigatório")
    private String address;

    @NotBlank(message = "Número é obrigatório")
    private String number;

    @NotBlank(message = "Bairro é obrigatório")
    private String neighborhood;

    @NotBlank(message = "Cidade é obrigatório")
    private String city;

    @Enumerated(EnumType.STRING)
    private StateEnum state;

    @NotNull(message = "CEP é obrigatório")
    private Long cep;

    public static Customer toDomain(CustomerDto updatedCustomer) {
        return Customer.builder()
                .id(updatedCustomer.getId())
                .name(updatedCustomer.getName())
                .cpf(updatedCustomer.getCpf())
                .rg(updatedCustomer.getRg())
                .email(updatedCustomer.getEmail())
                .phone(updatedCustomer.getPhone())
                .address(updatedCustomer.getAddress())
                .number(updatedCustomer.getNumber())
                .neighborhood(updatedCustomer.getNeighborhood())
                .city(updatedCustomer.getCity())
                .state(updatedCustomer.getState())
                .cep(updatedCustomer.getCep())
                .build();
    }

    public static CustomerDto toDto(Customer customer) {
        return CustomerDto.builder()
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
