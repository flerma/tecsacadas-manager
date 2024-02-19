package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUp;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversionsPerYearMonthWeekDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsMonthDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DaysOfWeekWithMoreConversionsYearDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Repository
@RequiredArgsConstructor
class LeadFollowUpRepositoryImpl implements LeadFollowUpRepository {

    private final LeadFollowUpDao leadFollowUpDao;

    @Override
    public List<LeadFollowUpEntity> findAll() {
        return leadFollowUpDao.findAll();
    }

    @Override
    public List<ConversionsPerYearMonthDto> findConversionsPerYearMonth(Integer year) {
        return leadFollowUpDao.findConversionsPerYearMonth(year).stream()
                .map(ConversionsPerYearMonthResponse::toDto)
                .toList();
    }

    @Override
    public List<ConversionsPerYearMonthWeekDto> findConversionsPerYearMonthWeek(Integer year) {
        return leadFollowUpDao.findConversionsPerYearMonthWeek(year).stream()
                .map(ConversionsPerYearMonthWeekResponse::toDto)
                .toList();
    }

    @Override
    public List<DaysOfWeekWithMoreConversionsMonthDto> findDaysOfWeekWithMoreConversionsMonth(Integer year, Integer month) {
        return leadFollowUpDao.findDaysOfWeekWithMoreConversionsMonth(year, month).stream()
                .map(DaysOfWeekWithMoreConversionsMonthResponse::toDto)
                .toList();
    }


    @Override
    public List<DaysOfWeekWithMoreConversionsYearDto> findDaysOfWeekWithMoreConversionsYear(Integer year) {
        return leadFollowUpDao.findDaysOfWeekWithMoreConversionsYear(year).stream()
                .map(DaysOfWeekWithMoreConversionsYearResponse::toDto)
                .toList();
    }

    @Override
    public Long findValidLeads(Integer year, Integer month) {
        return defaultIfNull(leadFollowUpDao.findValidLeads(year, month), 0L);
    }

    @Override
    public Long findInvalidLeads(Integer year, Integer month) {
        return defaultIfNull(leadFollowUpDao.findInvalidLeads(year, month), 0L);
    }

    @Override
    public void deleteAll() {
        leadFollowUpDao.deleteAll();
    }

    @Override
    public void saveAll(List<LeadFollowUp> leadFollowUpList) {
        List<LeadFollowUpEntity> leadFollowUpEntities = leadFollowUpList.stream()
                .map(LeadFollowUpEntity::toEntity)
                .toList();
        leadFollowUpDao.saveAll(leadFollowUpEntities);
    }

}
