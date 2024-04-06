package com.etlions.encryption.application.output.common;


import com.etlions.openapicodegen.dto.SuccessResponse;
import org.springframework.http.ResponseEntity;

public interface FindByIdPort<T> {
  ResponseEntity<SuccessResponse> findById(String id);
}
