package com.tecsacadas.tecsacadasmanager.controller;

import com.tecsacadas.tecsacadasmanager.exception.FileNotSupportedException;
import com.tecsacadas.tecsacadasmanager.service.AcompanhamentoDeLeadService;
import com.tecsacadas.tecsacadasmanager.validator.UploadValidator;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/acompanhamento-leads")
@CrossOrigin
public class AcompanhamentoDeLeadController {

    private final AcompanhamentoDeLeadService acompanhamentoLeadsService;
    private final UploadValidator uploadValidator;

    @Operation(summary = "Importar planilha de leads",
            description = "Permite importar a planilha de leads para a base de dados permitindo a geração de reletórios relacionados.")
    @ApiResponse(responseCode = "200",
            description = "Sucesso")
    @ApiResponse(responseCode = "404",
            description = "Solicitud no encontrada")
    @GetMapping("/importar")
    public ResponseEntity<Void> importar() {
        acompanhamentoLeadsService.importar();
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
        acompanhamentoLeadsService.upload(file);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/todos-relatorios/{ano}/{mes}")
    public ResponseEntity<Void> gerarTodosRelatorios(@PathVariable Integer ano, @PathVariable Integer mes) {
        acompanhamentoLeadsService.gerarTodosRelatorios(ano, mes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversoes-por-ano-mes/{ano}")
    public ResponseEntity<Void> gerarConversoesPorAnoMes(@PathVariable Integer ano) {
        acompanhamentoLeadsService.gerarConversoesPorAnoMes(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dias-semana-mais-conversoes-mes/{ano}/{mes}")
    public ResponseEntity<Void> gerarDiasSemanaComMaisConversoesMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        acompanhamentoLeadsService.gerarDiasSemanaComMaisConversoesMes(ano, mes);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/dias-semana-mais-conversoes-ano/{ano}")
    public ResponseEntity<Void> gerarDiasSemanaComMaisConversoesAno(@PathVariable Integer ano) {
        acompanhamentoLeadsService.gerarDiasSemanaComMaisConversoesAno(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/conversoes-por-ano-mes-semana/{ano}")
    public ResponseEntity<Void> gerarConversoesPorAnoMesSemana(@PathVariable Integer ano) {
        acompanhamentoLeadsService.gerarConversoesPorAnoMesSemana(ano);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/leads-invalidos-e-validos-por-ano-mes/{ano}/{mes}")
    public ResponseEntity<Void> gerarLeadsValidosXInvalidosPorAnoMes(@PathVariable Integer ano, @PathVariable Integer mes) {
        acompanhamentoLeadsService.gerarLeadsValidosXInvalidosPorAnoMes(ano, mes);
        return ResponseEntity.ok().build();
    }
}
