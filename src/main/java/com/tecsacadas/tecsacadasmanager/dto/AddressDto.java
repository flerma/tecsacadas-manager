package com.tecsacadas.tecsacadasmanager.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class AddressDto {

        @JsonProperty("cep")
        private String cep;

        @JsonProperty("logradouro")
        private String street;

        @JsonProperty("complemento")
        private String complement;

        @JsonProperty("bairro")
        private String neighborhood;

        @JsonProperty("localidade")
        private String location;

        @JsonProperty("uf")
        private String uf;

        @JsonProperty("ibge")
        private String ibge;

        @JsonProperty("gia")
        private String gia;

        @JsonProperty("ddd")
        private String ddd;

        @JsonProperty("siafi")
        private String siafi;
}
