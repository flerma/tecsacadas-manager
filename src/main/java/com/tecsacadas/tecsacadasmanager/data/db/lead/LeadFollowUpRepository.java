package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUp;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthWeekDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsMonthDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsYearDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeadFollowUpRepository {

    private final LeadFollowUpDao leadFollowUpDao;

    public List<LeadFollowUp> findAll() {
        return leadFollowUpDao.findAll();
    }

    public List<ConversionsPerYearMonthDto> findConversionsPerYearMonth(Integer year) {
        return leadFollowUpDao.findConversionsPerYearMonth(year).stream()
                .map(ConversionsPerYearMonthResponse::toDto)
                .toList();
    }

    public List<ConversionsPerYearMonthWeekDto> findConversionsPerYearMonthWeek(Integer year) {
        return leadFollowUpDao.findConversionsPerYearMonthWeek(year).stream()
                .map(ConversionsPerYearMonthWeekResponse::toDto)
                .toList();
    }

    public List<DaysOfWeekWithMoreConversionsMonthDto> findDaysOfWeekWithMoreConversionsMonth(Integer year, Integer month) {
        return leadFollowUpDao.findDaysOfWeekWithMoreConversionsMonth(year, month).stream()
                .map(DaysOfWeekWithMoreConversionsMonthResponse::toDto)
                .toList();
    }


    public List<DaysOfWeekWithMoreConversionsYearDto> findDaysOfWeekWithMoreConversionsYear(Integer year) {
        return leadFollowUpDao.findDaysOfWeekWithMoreConversionsYear(year).stream()
                .map(DaysOfWeekWithMoreConversionsYearResponse::toDto)
                .toList();
    }

    public Long findValidLeads(Integer year, Integer month) {
        return leadFollowUpDao.findValidLeads(year, month);
    }

    public Long findInvalidLeads(Integer year, Integer month) {
        return leadFollowUpDao.findInvalidLeads(year, month);
    }

    public void deleteAll() {
        leadFollowUpDao.deleteAll();
    }

    public void saveAll(List<LeadFollowUp> leadFollowUpList) {
        leadFollowUpDao.saveAll(leadFollowUpList);
    }

}
