package com.etlions.encryption.application.output.common;

import com.etlions.openapicodegen.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

public interface SavePort<T> {
  ResponseEntity<SuccessResponse> save(T entity);
}
