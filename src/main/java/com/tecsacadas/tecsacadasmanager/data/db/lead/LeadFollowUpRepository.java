package com.tecsacadas.tecsacadasmanager.data.db.lead;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUp;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversoesPorAnoMesDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.ConversoesPorMesAnoSemanaDto;
import com.tecsacadas.tecsacadasmanager.presentation.lead.DiasSemanaComMaisConversoesMesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LeadFollowUpRepository {

    private final LeadFollowUpDao leadFollowUpDao;

    public List<LeadFollowUp> findAll() {
        return leadFollowUpDao.findAll();
    }

    public List<ConversoesPorAnoMesDto> findConversoesPorMesAno(Integer ano) {
        return leadFollowUpDao.findConversoesPorMesAno(ano).stream()
                .map(ConversoesPorAnoMesResponse::toDto)
                .toList();
    }

    public List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesMes(Integer ano, Integer mes) {
        return leadFollowUpDao.findDiasSemanaComMaisConversoesMes(ano, mes).stream()
                .map(DiasSemanaComMaisConversoesMesResponse::toDto)
                .toList();
    }

    public List<ConversoesPorMesAnoSemanaDto> findConversoesPorMesAnoSemana(Integer ano) {
        return leadFollowUpDao.findConversoesPorMesAnoSemana(ano).stream()
                .map(ConversoesPorMesAnoSemanaResponse::toDto)
                .toList();
    }

    public List<DiasSemanaComMaisConversoesMesDto> findDiasSemanaComMaisConversoesAno(Integer ano) {
        return leadFollowUpDao.findDiasSemanaComMaisConversoesAno(ano).stream()
                .map(DiasSemanaComMaisConversoesMesResponse::toDto)
                .toList();
    }

    public Long findLeadsValidos(Integer ano, Integer mes) {
        return leadFollowUpDao.findLeadsValidos(ano, mes);
    }

    public Long findLeadsInvalidos(Integer ano, Integer mes) {
        return leadFollowUpDao.findLeadsInvalidos(ano, mes);
    }

    public void deleteAll() {
        leadFollowUpDao.deleteAll();
    }

    public void saveAll(List<LeadFollowUp> leadFollowUpList) {
        leadFollowUpDao.saveAll(leadFollowUpList);
    }

}
