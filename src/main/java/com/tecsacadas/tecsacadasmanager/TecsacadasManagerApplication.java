package com.tecsacadas.tecsacadasmanager;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@OpenAPIDefinition
@EnableFeignClients
@SpringBootApplication
public class TecsacadasManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TecsacadasManagerApplication.class, args);
    }

}
