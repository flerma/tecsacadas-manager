package com.tecsacadas.tecsacadasmanager.infrastructure.error.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request) {
        List<String> validationList = ex.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(validationList);
        return new ResponseEntity<>(errorResponse, status);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          @NotNull HttpHeaders headers,
                                                                          @NotNull HttpStatus status,
                                                                          @NotNull WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        String missingParam = ex.getParameterName();
        String errorMessage = String.format("O parâmetro '%s' é obrigatório", missingParam);
        errorResponse.setErrors(List.of(errorMessage));
        errorResponse.setStatus(HttpStatus.BAD_REQUEST);
        errorResponse.setMessage("Erro nos parâmetros da solicitação");
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(BadGatewayException.class)
    public ResponseEntity<String> handleBadGatewayException(BadGatewayException exc) {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("EXCEPTION: " + exc.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleCustomerNotExistException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleException(BusinessException ex) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ex.getMessage());
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<String> handleInternalServerErrorException(InternalServerErrorException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Arquivo muito grande! " + exc.getMaxUploadSize());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ErrorResponse {
        private HttpStatus status;
        private String message;
        private List<String> errors;
    }
}