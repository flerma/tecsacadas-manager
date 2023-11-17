package com.tecsacadas.tecsacadasmanager.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AcompanhamentoLeadColunas {

    DATA(0, "Data"),
    LOCAL(1, "String"),
    O_QUE_O_LEAD_PROCUROU(2, "String"),
    TIPO_SERVICO_DESEJADO(3, "String"),
    CLIENTE_POTENCIAL(4, "String"),
    MOTIVO_POR_NAO_SER_POTENCIAL(5, "String"),
    FORTMA_DE_CONTATO(6, "String"),
    STATUS(7, "String"),
    OBSERVACAO(8, "String"),
    ;

    private final Integer numeroColuna;
    private final String tipo;
}
