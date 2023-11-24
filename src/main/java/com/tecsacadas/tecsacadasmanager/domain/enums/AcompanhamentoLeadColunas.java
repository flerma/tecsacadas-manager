package com.tecsacadas.tecsacadasmanager.domain.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import static com.tecsacadas.tecsacadasmanager.domain.enums.AcompanhamentoLeadTipoColuna.DATA;

@Getter
@RequiredArgsConstructor
public enum AcompanhamentoLeadColunas {

    DATA(0, AcompanhamentoLeadTipoColuna.DATA),
    LOCAL(1, AcompanhamentoLeadTipoColuna.STRING),
    O_QUE_O_LEAD_PROCUROU(2, AcompanhamentoLeadTipoColuna.STRING),
    TIPO_SERVICO_DESEJADO(3, AcompanhamentoLeadTipoColuna.STRING),
    CLIENTE_POTENCIAL(4, AcompanhamentoLeadTipoColuna.STRING),
    MOTIVO_POR_NAO_SER_POTENCIAL(5, AcompanhamentoLeadTipoColuna.STRING),
    FORTMA_DE_CONTATO(6, AcompanhamentoLeadTipoColuna.STRING),
    STATUS(7, AcompanhamentoLeadTipoColuna.STRING),
    OBSERVACAO(8, AcompanhamentoLeadTipoColuna.STRING),
    ;

    private final Integer numeroColuna;
    private final AcompanhamentoLeadTipoColuna tipoColuna;

    public static AcompanhamentoLeadColunas getColuna(Integer numeroColuna) {
        for (AcompanhamentoLeadColunas coluna : AcompanhamentoLeadColunas.values()) {
            if (coluna.getNumeroColuna().equals(numeroColuna))
                return coluna;
        }
        return null;
    }
}
