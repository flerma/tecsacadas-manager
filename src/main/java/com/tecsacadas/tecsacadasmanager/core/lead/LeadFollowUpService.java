package com.tecsacadas.tecsacadasmanager.core.lead;

import com.tecsacadas.tecsacadasmanager.core.report.LeadFileImportService;
import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import com.tecsacadas.tecsacadasmanager.infrastructure.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUpReportIdentifiers.getIdentifier;

@Service
@RequiredArgsConstructor
public class LeadFollowUpService {

    private final LeadFollowUpRepository leadFollowUpRepository;
    private final DaysOfWeekWithMoreConversionsYearService daysOfWeekWithMoreConversionsYearService;
    private final DaysOfWeekWithMoreConversionsMonthService daysOfWeekWithMoreConversionsMonthService;
    private final ConversionsPerYearMonthService conversionsPerYearMonthService;
    private final ConversionsPerYearMonthWeekService conversionsPerYearMonthWeekService;
    private final ValidXInvalidLeadsPerYearMonthService validXInvalidLeadsPerYearMonthService;
    private final LeadFileImportService leadFileImportService;
    private final LeadFollowUpReportFactory leadFollowUpReportFactory;

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

    public void generateAllReports(@NotNull Integer year, @NotNull Integer month) {

        CompletableFuture<?>[] tasks = Stream.of(
                CompletableFuture.runAsync(() -> conversionsPerYearMonthWeekService.generate(year)),
                CompletableFuture.runAsync(() -> conversionsPerYearMonthService.generate(year)),
                CompletableFuture.runAsync(() -> daysOfWeekWithMoreConversionsYearService.generate(year)),
                CompletableFuture.runAsync(() -> validXInvalidLeadsPerYearMonthService.generate(year, month)),
                CompletableFuture.runAsync(() -> daysOfWeekWithMoreConversionsMonthService.generate(year, month))
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
    }

    public void generateDaysOfWeekWithMoreConversionsYear(@NotNull Integer year) {
        daysOfWeekWithMoreConversionsYearService.generate(year);
    }

    public void generateDaysOfWeekWithMoreConversionsMonth(@NotNull Integer year,
                                                           @NotNull Integer month) {
        daysOfWeekWithMoreConversionsMonthService.generate(year, month);
    }

    public void generateConversionsPerYearMonth(@NotNull Integer year) {
        conversionsPerYearMonthService.generate(year);
    }

    public void generateConversionsPerYearMonthWeek(@NotNull Integer year) {
        conversionsPerYearMonthWeekService.generate(year);
    }

    public void generateValidXInvalidLeadsPerYearMonth(@NotNull Integer year,
                                                       @NotNull Integer month) {
        validXInvalidLeadsPerYearMonthService.generate(year, month);
    }

    public ByteArrayInputStream downloadReportByIdentifier(@NotNull String identifier,
                                                           @NotNull Integer year,
                                                           @Nullable Integer month) {
        LeadFollowUpReportIdentifiers identifierStrate = getIdentifier(identifier);
        if (identifierStrate.getIsReportParameterYearAndMonth() && month != null)
            return identifierStrate.execute(year, month, leadFollowUpReportFactory);
        else if (identifierStrate.getIsReportParameterYear())
            return identifierStrate.execute(year, leadFollowUpReportFactory);
        else
            throw new BusinessException("Parâmetros inválidos para o relatório");
    }
}
