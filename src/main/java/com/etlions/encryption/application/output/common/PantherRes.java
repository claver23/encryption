package com.etlions.encryption.application.output.common;

import org.springframework.http.ResponseEntity;

public interface PantherRes<T> {

  ResponseEntity<T> dto(Object data, String message);

}
