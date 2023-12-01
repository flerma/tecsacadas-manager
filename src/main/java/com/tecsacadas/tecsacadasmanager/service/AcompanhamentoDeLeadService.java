package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.AcompanhamentoLead;
import com.tecsacadas.tecsacadasmanager.repository.AcompanhamentoLeadRepository;
import com.tecsacadas.tecsacadasmanager.service.relatorios.ConversoesPorAnoMesSemana;
import com.tecsacadas.tecsacadasmanager.service.relatorios.ConversoesPorAnoMesService;
import com.tecsacadas.tecsacadasmanager.service.relatorios.DiasSemanaComMaisConversoesAnoService;
import com.tecsacadas.tecsacadasmanager.service.relatorios.DiasSemanaComMaisConversoesMesService;
import com.tecsacadas.tecsacadasmanager.service.relatorios.ImportacaoArquivoLeadsService;
import com.tecsacadas.tecsacadasmanager.service.relatorios.LeadInvalidoXValidoPorMesAnoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AcompanhamentoDeLeadService {

    private final AcompanhamentoLeadRepository acompanhamentoLeadRepository;
    private final DiasSemanaComMaisConversoesAnoService diasSemanaComMaisConversoesAnoService;
    private final DiasSemanaComMaisConversoesMesService diasSemanaComMaisConversoesMesService;
    private final ConversoesPorAnoMesService conversoesPorAnoMesService;
    private final ConversoesPorAnoMesSemana conversoesPorAnoMesSemanaService;
    private final LeadInvalidoXValidoPorMesAnoService leadInvalidoXValidoPorMesAnoService;
    private final ImportacaoArquivoLeadsService importacaoArquivoLeadsService;

    @SneakyThrows
    public void importar() {
        var acompanhamentoLeadList =  importacaoArquivoLeadsService.lerArquivo();
        salvar(acompanhamentoLeadList);
    }

    private void salvar(List<AcompanhamentoLead> acompanhamentoLeadList) {
        acompanhamentoLeadRepository.deleteAll();
        acompanhamentoLeadRepository.saveAll(acompanhamentoLeadList);
    }

    public void gerarTodosRelatorios(Integer ano, Integer mes) {
        conversoesPorAnoMesSemanaService.gerar(ano);
        conversoesPorAnoMesService.gerar(ano);
        diasSemanaComMaisConversoesAnoService.gerar(ano);
        leadInvalidoXValidoPorMesAnoService.gerar(ano, mes);
        diasSemanaComMaisConversoesMesService.gerar(ano, mes);
    }

    public void gerarDiasSemanaComMaisConversoesAno(Integer ano) {
        diasSemanaComMaisConversoesAnoService.gerar(ano);
    }

    public void gerarDiasSemanaComMaisConversoesMes(Integer ano, Integer mes) {
        diasSemanaComMaisConversoesMesService.gerar(ano, mes);
    }

    public void gerarConversoesPorAnoMes(Integer ano) {
        conversoesPorAnoMesService.gerar(ano);
    }

    public void gerarConversoesPorAnoMesSemana(Integer ano) {
        conversoesPorAnoMesSemanaService.gerar(ano);
    }

    public void gerarLeadsValidosXInvalidosPorAnoMes(Integer ano, Integer mes) {
        leadInvalidoXValidoPorMesAnoService.gerar(ano, mes);
    }
}
