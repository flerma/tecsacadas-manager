package com.tecsacadas.tecsacadasmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversoesPorMesAnoSemanaDto {

    private Integer ano;
    private Integer mes;
    private Integer semana;
    private Long conversoes;
}
