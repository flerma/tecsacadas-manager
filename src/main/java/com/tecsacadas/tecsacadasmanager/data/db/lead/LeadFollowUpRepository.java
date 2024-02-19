package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUp;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthWeekDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsMonthDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsYearDto;

import java.util.List;

public interface LeadFollowUpRepository {
    List<LeadFollowUpEntity> findAll();

    List<ConversionsPerYearMonthDto> findConversionsPerYearMonth(Integer year);

    List<ConversionsPerYearMonthWeekDto> findConversionsPerYearMonthWeek(Integer year);

    List<DaysOfWeekWithMoreConversionsMonthDto> findDaysOfWeekWithMoreConversionsMonth(Integer year, Integer month);

    List<DaysOfWeekWithMoreConversionsYearDto> findDaysOfWeekWithMoreConversionsYear(Integer year);

    Long findValidLeads(Integer year, Integer month);

    Long findInvalidLeads(Integer year, Integer month);

    void deleteAll();

    void saveAll(List<LeadFollowUp> leadFollowUpList);
}
