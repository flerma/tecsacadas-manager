package com.tecsacadas.tecsacadasmanager.core.lead;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LeadFollowUpColumns {

    DATA(0, LeadFollowUpColumnType.DATE),
    LOCAL(1, LeadFollowUpColumnType.STRING),
    O_QUE_O_LEAD_PROCUROU(2, LeadFollowUpColumnType.STRING),
    TIPO_SERVICO_DESEJADO(3, LeadFollowUpColumnType.STRING),
    CLIENTE_POTENCIAL(4, LeadFollowUpColumnType.STRING),
    MOTIVO_POR_NAO_SER_POTENCIAL(5, LeadFollowUpColumnType.STRING),
    FORTMA_DE_CONTATO(6, LeadFollowUpColumnType.STRING),
    STATUS(7, LeadFollowUpColumnType.STRING),
    NOTE(8, LeadFollowUpColumnType.STRING),
    ;

    private final Integer columnNumber;
    private final LeadFollowUpColumnType columnType;
}
