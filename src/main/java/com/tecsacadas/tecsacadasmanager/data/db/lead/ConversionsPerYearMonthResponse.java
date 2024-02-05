package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ConversionsPerYearMonthResponse {

    private Integer year;
    private Integer month;
    private Long conversions;

    public ConversionsPerYearMonthDto toDto() {
        return ConversionsPerYearMonthDto.builder()
                .year(year)
                .month(month)
                .conversions(conversions)
                .build();
    }
}
