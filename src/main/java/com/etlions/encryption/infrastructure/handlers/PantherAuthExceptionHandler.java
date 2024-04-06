package com.etlions.encryption.infrastructure.handlers;

import com.etlions.encryption.domain.exception.PantherDecryptionException;
import com.etlions.encryption.domain.exception.PantherEncryptionException;
import com.etlions.openapicodegen.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.etlions.encryption.domain.utils.Constants.COULD_NOT_DECRYPT_KEY;
import static com.etlions.encryption.domain.utils.Constants.COULD_NOT_ENCRYPT_KEY;

@Slf4j
@RestControllerAdvice
public class PantherAuthExceptionHandler {

  @ExceptionHandler(WebExchangeBindException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handleWebExchangeBindException(
      WebExchangeBindException ex, ServerWebExchange exchange) {
    Set<String> errorMessages = Stream.of(
            ex.getAllErrors().stream().map(objectError -> objectError.getDefaultMessage()),
            ex.getGlobalErrors().stream().map(objectError -> objectError.getDefaultMessage()),
            ex.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage())
        )
        .flatMap(stream -> stream)
        .collect(Collectors.toSet());

    return ResponseEntity.badRequest()
        .body(ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.toString())
            .message(ex.getReason())
            .timestamp(new Date())
            .errors(errorMessages.stream().toList())
            .build());
  }

  @ExceptionHandler(PantherEncryptionException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handlePantherEncryptionException(
      PantherEncryptionException ex, ServerWebExchange exchange) {
    return ResponseEntity.badRequest()
        .body(ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.toString())
            .message(COULD_NOT_ENCRYPT_KEY)
            .timestamp(new Date())
            .errors(Collections.singletonList(ex.getMessage()))
            .build());
  }

  @ExceptionHandler(PantherDecryptionException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ErrorResponse> handlePantherDecryptionException(
      PantherDecryptionException ex, ServerWebExchange exchange) {
    return ResponseEntity.badRequest()
        .body(ErrorResponse.builder()
            .code(HttpStatus.BAD_REQUEST.toString())
            .message(COULD_NOT_DECRYPT_KEY)
            .timestamp(new Date())
            .errors(Collections.singletonList(ex.getMessage()))
            .build());
  }
}