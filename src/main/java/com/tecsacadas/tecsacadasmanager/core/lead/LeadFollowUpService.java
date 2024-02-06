package com.tecsacadas.tecsacadasmanager.core.lead;

import com.tecsacadas.tecsacadasmanager.core.report.ConversionsPerYearMonthService;
import com.tecsacadas.tecsacadasmanager.core.report.ConversionsPerYearMonthWeekService;
import com.tecsacadas.tecsacadasmanager.core.report.DaysOfWeekWithMoreConversionsMonthService;
import com.tecsacadas.tecsacadasmanager.core.report.DaysOfWeekWithMoreConversionsYearService;
import com.tecsacadas.tecsacadasmanager.core.report.LeadFileImportService;
import com.tecsacadas.tecsacadasmanager.core.report.ValidXInvalidLeadsPerYearMonthService;
import com.tecsacadas.tecsacadasmanager.data.db.lead.LeadFollowUpRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

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

    public void generateAllReports(Integer year, Integer month) {

        CompletableFuture<?>[] tasks = Stream.of(
                CompletableFuture.runAsync(() -> conversionsPerYearMonthWeekService.generate(year)),
                CompletableFuture.runAsync(() -> conversionsPerYearMonthService.generate(year)),
                CompletableFuture.runAsync(() -> daysOfWeekWithMoreConversionsYearService.generate(year)),
                CompletableFuture.runAsync(() -> validXInvalidLeadsPerYearMonthService.generate(year, month)),
                CompletableFuture.runAsync(() -> daysOfWeekWithMoreConversionsMonthService.generate(year, month))
        ).toArray(CompletableFuture[]::new);

        CompletableFuture.allOf(tasks).join();
    }

    public void generateDaysOfWeekWithMoreConversionsYear(Integer year) {
        daysOfWeekWithMoreConversionsYearService.generate(year);
    }

    public ByteArrayInputStream generateDownloadDaysOfWeekWithMoreConversionsYear(Integer year) {
        return daysOfWeekWithMoreConversionsYearService.generate(year);
    }

    public void generateDaysOfWeekWithMoreConversionsMonth(Integer year, Integer month) {
        daysOfWeekWithMoreConversionsMonthService.generate(year, month);
    }

    public void generateConversionsPerYearMonth(Integer year) {
        conversionsPerYearMonthService.generate(year);
    }

    public void generateConversionsPerYearMonthWeek(Integer year) {
        conversionsPerYearMonthWeekService.generate(year);
    }

    public void generateValidXInvalidLeadsPerYearMonth(Integer year, Integer month) {
        validXInvalidLeadsPerYearMonthService.generate(year, month);
    }
}
