package com.tecsacadas.tecsacadasmanager.presentation.lead;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConversionsPerYearMonthWeekDto {

    private Integer year;
    private Integer month;
    private Integer week;
    private Long conversions;
}
