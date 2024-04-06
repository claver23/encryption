package com.etlions.encryption.application.output.common;

import com.etlions.openapicodegen.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

public interface FindAllPort<T> {
  ResponseEntity<SuccessResponse> findAll();
}
