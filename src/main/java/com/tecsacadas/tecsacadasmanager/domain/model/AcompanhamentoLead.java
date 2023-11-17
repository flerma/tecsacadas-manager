package com.tecsacadas.tecsacadasmanager.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AcompanhamentoLead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
