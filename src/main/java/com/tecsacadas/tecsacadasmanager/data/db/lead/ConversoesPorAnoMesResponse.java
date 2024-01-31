package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversoesPorAnoMesDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ConversoesPorAnoMesResponse {

    private Integer ano;
    private Integer mes;
    private Long conversoes;

    public ConversoesPorAnoMesDto toDto() {
        return ConversoesPorAnoMesDto.builder()
                .ano(ano)
                .mes(mes)
                .conversoes(conversoes)
                .build();
    }
}
