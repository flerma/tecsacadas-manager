package com.tecsacadas.tecsacadasmanager.core.lead;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeadFollowUp {

    private Long id;
    private LocalDate dataContato;
    private String localOrigem;
    private String procurou;
    private String tipoServico;
    private String clientePotencial;
    private String motivoNaoSerPotencial;
    private String formaContato;
    private String status;
    private String observacao;
}
