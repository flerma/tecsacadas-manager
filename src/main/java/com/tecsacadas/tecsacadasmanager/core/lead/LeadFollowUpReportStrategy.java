package com.tecsacadas.tecsacadasmanager.core.lead;

import java.io.ByteArrayInputStream;

public interface LeadFollowUpReportStrategy {
    default ByteArrayInputStream download(Integer year, Integer month) {
        return null;
    }

    default ByteArrayInputStream download(Integer year) {
        return null;
    }

    default void generate(Integer year, Integer month) {

    }
    default void generate(Integer year) {

    }
}
