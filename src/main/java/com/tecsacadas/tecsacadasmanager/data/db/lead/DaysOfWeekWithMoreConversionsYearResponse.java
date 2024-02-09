package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsYearDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class DaysOfWeekWithMoreConversionsYearResponse {

    private LocalDate date;
    private String dayOfWeek;
    private Long conversions;
    public DaysOfWeekWithMoreConversionsYearDto toDto() {
        return DaysOfWeekWithMoreConversionsYearDto.builder()
                .date(date)
                .dayOfWeek(dayOfWeek)
                .conversions(conversions)
                .build();
    }
}
