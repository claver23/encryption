package com.etlions.encryption.domain.utils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import static com.etlions.encryption.domain.utils.Constants.UNSUPPORTED_OPERATION_FOR_ALGORITHM;

public abstract class PantherEncryptionAlgorithm implements PantherEncryption {

  public abstract Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException;


  protected PublicKey getPublicKey() throws InvalidKeySpecException {
    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_FOR_ALGORITHM);
  }

  protected PrivateKey getPrivateKey() throws InvalidKeySpecException {
    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_FOR_ALGORITHM);
  }

  protected SecretKey getSecretKey() {
    throw new UnsupportedOperationException(UNSUPPORTED_OPERATION_FOR_ALGORITHM);
  }
}
