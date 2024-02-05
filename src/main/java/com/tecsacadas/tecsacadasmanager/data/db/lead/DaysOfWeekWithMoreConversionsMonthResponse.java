package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsMonthDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class DaysOfWeekWithMoreConversionsMonthResponse {

    private LocalDate date;
    private String dayOfWeek;
    private Long conversions;
    public DaysOfWeekWithMoreConversionsMonthDto toDto() {
        return DaysOfWeekWithMoreConversionsMonthDto.builder()
                .date(date)
                .dayOfWeek(dayOfWeek)
                .conversions(conversions)
                .build();
    }
}
