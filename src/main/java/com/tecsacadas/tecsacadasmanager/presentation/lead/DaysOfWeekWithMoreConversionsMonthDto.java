package com.tecsacadas.tecsacadasmanager.presentation.lead;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DaysOfWeekWithMoreConversionsMonthDto {

    private LocalDate date;
    private String dayOfWeek;
    private Long conversions;

}
