package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUp;
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
class LeadFollowUpEntity {

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

    static LeadFollowUpEntity toEntity(LeadFollowUp leadFollowUp) {
        return LeadFollowUpEntity.builder()
                .id(leadFollowUp.getId())
                .dataContato(leadFollowUp.getDataContato())
                .localOrigem(leadFollowUp.getLocalOrigem())
                .procurou(leadFollowUp.getProcurou())
                .tipoServico(leadFollowUp.getTipoServico())
                .clientePotencial(leadFollowUp.getClientePotencial())
                .motivoNaoSerPotencial(leadFollowUp.getMotivoNaoSerPotencial())
                .formaContato(leadFollowUp.getFormaContato())
                .status(leadFollowUp.getStatus())
                .observacao(leadFollowUp.getObservacao())
                .build();
    }
}
