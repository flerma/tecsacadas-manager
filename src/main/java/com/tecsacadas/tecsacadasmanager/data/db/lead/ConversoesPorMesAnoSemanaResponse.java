package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversoesPorMesAnoSemanaDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DiasSemanaComMaisConversoesMesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ConversoesPorMesAnoSemanaResponse {

    private Integer ano;
    private Integer mes;
    private Integer semana;
    private Long conversoes;

    public ConversoesPorMesAnoSemanaDto toDto() {
        return ConversoesPorMesAnoSemanaDto.builder()
                .ano(ano)
                .mes(mes)
                .semana(semana)
                .conversoes(conversoes)
                .build();
    }
}
