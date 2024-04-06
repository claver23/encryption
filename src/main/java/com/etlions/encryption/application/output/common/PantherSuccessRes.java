package com.etlions.encryption.application.output.common;

import com.etlions.openapicodegen.dto.SuccessResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Date;

public class PantherSuccessRes implements PantherRes<SuccessResponse> {

  /**
   * @param data
   * @return
   */
  @Override
  public ResponseEntity<SuccessResponse> dto(Object data, String message) {
    return ResponseEntity.ok().body(SuccessResponse.builder()
        .isSuccess(true)
        .status(HttpStatus.OK.toString())
        .message(message)
        .timestamp(new Date())
        .data(data)
        .build());
  }
}
