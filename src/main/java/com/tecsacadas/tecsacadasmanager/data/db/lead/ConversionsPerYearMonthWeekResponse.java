package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthWeekDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class ConversionsPerYearMonthWeekResponse {

    private Integer year;
    private Integer month;
    private Integer week;
    private Long conversions;

    public ConversionsPerYearMonthWeekDto toDto() {
        return ConversionsPerYearMonthWeekDto.builder()
                .year(year)
                .month(month)
                .week(week)
                .conversions(conversions)
                .build();
    }
}
