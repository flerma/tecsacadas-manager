package com.tecsacadas.tecsacadasmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiasSemanaComMaisConversoesMesDto {

    private LocalDate data;
    private String diaSemana;
    private Long conversoes;

}
