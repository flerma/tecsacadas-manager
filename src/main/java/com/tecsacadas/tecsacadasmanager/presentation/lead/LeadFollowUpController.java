package com.tecsacadas.tecsacadasmanager.presentation.lead;

import com.tecsacadas.tecsacadasmanager.core.lead.LeadFollowUpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.extensions.Extension;
import io.swagger.v3.oas.annotations.extensions.ExtensionProperty;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acompanhamento-leads")
@CrossOrigin
public class LeadFollowUpController {

    private final LeadFollowUpService leadFollowUpService;

    @Operation(summary = "Importar planilha de leads",
            description = "Permite importar a planilha de leads para a base de dados permitindo a geração de reletórios relacionados.")
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @ApiResponse(responseCode = "404",
            description = "Solicitud no encontrada")
    @GetMapping("/importar")
    public ResponseEntity<Void> importar() {
        leadFollowUpService.importFile();
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Importar planilha de leads",
            description = "Permite importar a planilha de leads para a base de dados permitindo a geração de reletórios relacionados.")
    @Parameter(name = "file",
               description = "Arquivo excel de leads a ser importado.",
               required = true,
               extensions = @Extension(name = "allowedTypes",
                                       properties = @ExtensionProperty(name = "xlsx",
                                                                       value = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")),
               content = @Content(mediaType = "multipart/form-data",
                                  schema = @Schema(type = "multipart/form-data")),
               example = "leads.xlsx")
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @ApiResponse(responseCode = "400",
            description = "Arquivo ou formato errado")
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Void> upload(@RequestPart("file") MultipartFile file) {
        UploadValidator.validate(file);
        leadFollowUpService.uploadFile(file);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gera todos os relatórios de leads por ano e mes.",
            description = "Chamada criada para gerar todos os relatórios de leads de uma só vez.")
    @Parameter(name = "ano",
            description = "Ano em que o lead foi gerardo.",
            required = true)
    @Parameter(name = "mes",
            description = "Mês em que o lead foi gerardo.",
            required = true)
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @GetMapping("/todos-relatorios")
    public ResponseEntity<Void> generateAllReports(@Valid @RequestParam("ano") @Min(2023) @Max(2100) @NotNull(message = "Ano deve ser informado") Integer year,
                                                   @Valid @RequestParam("mes") @Min(1) @Max(12) @NotNull(message = "Mês deve ser informado") Integer month)  {
        leadFollowUpService.generateAllReports(year, month);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gera reletório de conversões por ano e mes.",
            description = "Chamada criada para gera od relatório de conversões por ano e mes.")
    @Parameter(name = "ano",
            description = "Ano em que o lead foi gerardo.",
            required = true)
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @GetMapping("/conversoes-por-ano-mes/{ano}")
    public ResponseEntity<Void> generateConversionsPerYearMonth(@PathVariable Integer ano) {
        leadFollowUpService.generateConversionsPerYearMonth(ano);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gera relatório de dias da semana com mais conversões por mes.",
            description = "Chamada criada para gerar relatório que informa os dias da semana no mês com mais conversões.")
    @Parameter(name = "ano",
            description = "Ano em que o lead foi gerardo.",
            required = true)
    @Parameter(name = "mes",
            description = "Mês em que o lead foi gerardo.",
            required = true)
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @GetMapping("/dias-semana-mais-conversoes-mes/{ano}/{mes}")
    public ResponseEntity<Void> generateDaysOfWeekWithMoreConversionsMonth(@PathVariable Integer ano, @PathVariable Integer mes) {
        leadFollowUpService.generateDaysOfWeekWithMoreConversionsMonth(ano, mes);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gera relatório de dias da semana com mais conversões no ano.",
            description = "Chamada criada para gerar relatório que informa os dias da semana no ano com mais conversões.")
    @Parameter(name = "ano",
            description = "Ano em que o lead foi gerardo.",
            required = true)
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @GetMapping("/dias-semana-mais-conversoes-ano/{ano}")
    public ResponseEntity<Void> generateDaysOfWeekWithMoreConversionsYear(@PathVariable Integer ano) {
        leadFollowUpService.generateDaysOfWeekWithMoreConversionsYear(ano);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gera relatório das conversões por ano, mês e semana.",
            description = "Chamada criada para gerar relatório que informa as conversões por ano, mês e semana.")
    @Parameter(name = "ano",
            description = "Ano em que o lead foi gerardo.",
            required = true)
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @GetMapping("/conversoes-por-ano-mes-semana/{ano}")
    public ResponseEntity<Void> generateConversionsPerYearMonthWeek(@PathVariable Integer ano) {
        leadFollowUpService.generateConversionsPerYearMonthWeek(ano);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Gera relatório os leads inválidos contra válidos por ano e mês.",
            description = "Chamada criada para gerar relatório os leads inválidos contra válidos por ano e mês.")
    @Parameter(name = "ano",
            description = "Ano em que o lead foi gerardo.",
            required = true)
    @Parameter(name = "mes",
            description = "Mês em que o lead foi gerardo.",
            required = true)
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @GetMapping("/leads-invalidos-e-validos-por-ano-mes/{ano}/{mes}")
    public ResponseEntity<Void> generateValidXInvalidLeadsPerYearMonth(@PathVariable Integer ano, @PathVariable Integer mes) {
        leadFollowUpService.generateValidXInvalidLeadsPerYearMonth(ano, mes);
        return ResponseEntity.ok().build();
    }
}
