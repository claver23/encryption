package com.etlions.encryption.domain.exception;

public class PantherEncryptionException extends RuntimeException {

  public PantherEncryptionException(String message) {
    super(message);
  }

  public PantherEncryptionException(String message, Throwable cause) {
    super(message, cause);
  }
}