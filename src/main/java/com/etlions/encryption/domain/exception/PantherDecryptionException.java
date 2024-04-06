package com.etlions.encryption.domain.exception;

public class PantherDecryptionException extends RuntimeException {

  public PantherDecryptionException(String message) {
    super(message);
  }

  public PantherDecryptionException(String message, Throwable cause) {
    super(message, cause);
  }
}