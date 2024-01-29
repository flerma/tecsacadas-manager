package com.tecsacadas.tecsacadasmanager.service;

import com.tecsacadas.tecsacadasmanager.domain.model.LeadFollowUp;
import com.tecsacadas.tecsacadasmanager.repository.LeadFollowUpRepository;
import com.tecsacadas.tecsacadasmanager.service.reports.ConversoesPorAnoMesSemana;
import com.tecsacadas.tecsacadasmanager.service.reports.ConversoesPorAnoMesService;
import com.tecsacadas.tecsacadasmanager.service.reports.DiasSemanaComMaisConversoesAnoService;
import com.tecsacadas.tecsacadasmanager.service.reports.DiasSemanaComMaisConversoesMesService;
import com.tecsacadas.tecsacadasmanager.service.reports.LeadFileImportService;
import com.tecsacadas.tecsacadasmanager.service.reports.LeadInvalidoXValidoPorMesAnoService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class LeadFollowUpService {

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final DiasSemanaComMaisConversoesAnoService diasSemanaComMaisConversoesAnoService;
    private final DiasSemanaComMaisConversoesMesService diasSemanaComMaisConversoesMesService;
    private final ConversoesPorAnoMesService conversoesPorAnoMesService;
    private final ConversoesPorAnoMesSemana conversoesPorAnoMesSemanaService;
    private final LeadInvalidoXValidoPorMesAnoService leadInvalidoXValidoPorMesAnoService;
    private final LeadFileImportService leadFileImportService;

    @SneakyThrows
    public void importFile() {
        var leadFollowUpList =  leadFileImportService.readFile();
        save(leadFollowUpList);
    }

    @SneakyThrows
    public void uploadFile(MultipartFile file) {
        InputStream inputStream = file.getInputStream();
        var leadFollowUpList =  leadFileImportService.readFile(inputStream);
        save(leadFollowUpList);
        inputStream.close();
    }

    private void save(List<LeadFollowUp> leadFollowUpList) {
        leadFollowUpRepository.deleteAll();
        leadFollowUpRepository.saveAll(leadFollowUpList);
    }

    public void gerarTodosRelatorios(Integer year, Integer month) {

        CompletableFuture<?>[] tasks = Stream.of(
                CompletableFuture.runAsync(() -> conversoesPorAnoMesSemanaService.generate(year)),
                CompletableFuture.runAsync(() -> conversoesPorAnoMesService.generate(year)),
                CompletableFuture.runAsync(() -> diasSemanaComMaisConversoesAnoService.generate(year)),
                CompletableFuture.runAsync(() -> leadInvalidoXValidoPorMesAnoService.generate(year, month)),
                CompletableFuture.runAsync(() -> diasSemanaComMaisConversoesMesService.generate(year, month))
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
    }

    public void gerarDiasSemanaComMaisConversoesAno(Integer year) {
        diasSemanaComMaisConversoesAnoService.generate(year);
    }

    public void gerarDiasSemanaComMaisConversoesMes(Integer year, Integer month) {
        diasSemanaComMaisConversoesMesService.generate(year, month);
    }

    public void gerarConversoesPorAnoMes(Integer year) {
        conversoesPorAnoMesService.generate(year);
    }

    public void gerarConversoesPorAnoMesSemana(Integer year) {
        conversoesPorAnoMesSemanaService.generate(year);
    }

    public void gerarLeadsValidosXInvalidosPorAnoMes(Integer year, Integer month) {
        leadInvalidoXValidoPorMesAnoService.generate(year, month);
    }
}
