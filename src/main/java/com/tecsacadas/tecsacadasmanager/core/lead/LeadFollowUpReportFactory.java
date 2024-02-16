package com.tecsacadas.tecsacadasmanager.core.lead;

import com.tecsacadas.tecsacadasmanager.core.report.ExcelService;
import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class LeadFollowUpReportFactory implements Function<LeadFollowUpReportIdentifiers, LeadFollowUpReportStrategy> {

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final ExcelService excelService;

    @Override
    public LeadFollowUpReportStrategy apply(LeadFollowUpReportIdentifiers identifiers) {
        switch (identifiers) {
            case CONVERSIONS_PER_YEAR_MONTH_WEEK:
                return new ConversionsPerYearMonthWeekService(leadFollowUpRepository, excelService);
            case CONVERSIONS_PER_YEAR_MONTH:
                return new ConversionsPerYearMonthService(leadFollowUpRepository, excelService);
            case DAYS_OF_WEEK_WITH_MORE_CONVERSIONS_YEAR:
                return new DaysOfWeekWithMoreConversionsYearService(leadFollowUpRepository, excelService);
            case DAYS_OF_WEEK_WITH_MORE_CONVERSIONS_MONTH:
                return new DaysOfWeekWithMoreConversionsMonthService(leadFollowUpRepository, excelService);
            case VALID_X_INVALID_LEADS_PER_YEAR_MONTH:
                return new ValidXInvalidLeadsPerYearMonthService(leadFollowUpRepository, excelService);
            default:
                throw new IllegalArgumentException("Rerlatorio não encontrado");
        }
    }
}
