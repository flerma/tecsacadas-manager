package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.DiasSemanaComMaisConversoesMesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class DiasSemanaComMaisConversoesMesResponse {

    private LocalDate data;
    private String diaSemana;
    private Long conversoes;
    public DiasSemanaComMaisConversoesMesDto toDto() {
        return DiasSemanaComMaisConversoesMesDto.builder()
                .data(data)
                .diaSemana(diaSemana)
                .conversoes(conversoes)
                .build();
    }
}
