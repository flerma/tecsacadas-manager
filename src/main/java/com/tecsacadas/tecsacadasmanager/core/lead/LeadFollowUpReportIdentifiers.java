package com.tecsacadas.tecsacadasmanager.core.lead;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;

@Getter
@RequiredArgsConstructor
public enum LeadFollowUpReportIdentifiers {

    CONVERSIONS_PER_YEAR_MONTH_WEEK(true, false),
    CONVERSIONS_PER_YEAR_MONTH(true, false),
    DAYS_OF_WEEK_WITH_MORE_CONVERSIONS_MONTH(false, true),
    DAYS_OF_WEEK_WITH_MORE_CONVERSIONS_YEAR(true, false),
    VALID_X_INVALID_LEADS_PER_YEAR_MONTH(false, true);

    private final Boolean isReportParameterYear;
    private final Boolean isReportParameterYearAndMonth;

    public ByteArrayInputStream execute(Integer year,
                                        Integer month,
                                        LeadFollowUpReportFactory factory) {
        LeadFollowUpReportStrategy service = factory.apply(this);
        return service.download(year, month);
    }

    public ByteArrayInputStream execute(Integer year,
                                        LeadFollowUpReportFactory factory) {
        LeadFollowUpReportStrategy service = factory.apply(this);
        return service.download(year);
    }

    public static LeadFollowUpReportIdentifiers getIdentifier(String identifier) {
        return LeadFollowUpReportIdentifiers.valueOf(identifier);
    }
}
