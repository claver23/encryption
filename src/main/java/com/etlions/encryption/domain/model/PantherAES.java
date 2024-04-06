package com.etlions.encryption.domain.model;

import com.etlions.encryption.domain.exception.PantherDecryptionException;
import com.etlions.encryption.domain.exception.PantherEncryptionException;
import com.etlions.encryption.domain.utils.PantherEncryptionAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Slf4j
public class PantherAES extends PantherEncryptionAlgorithm {

  protected final SecretKey secretKey;

  public PantherAES(SecretKey secretKey) {
    this.secretKey = secretKey;
  }

  @Override
  public Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
    return Cipher.getInstance("AES");
  }

  @Override
  protected SecretKey getSecretKey() {
    return this.secretKey;
  }

  @Override
  public String encrypt(String wordToEncrypt) {
    try {
      Cipher cipher = getCipher();
      cipher.init(Cipher.ENCRYPT_MODE, getSecretKey());
      byte[] encryptedBytes = cipher.doFinal(wordToEncrypt.getBytes());
      return Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
             IllegalBlockSizeException | BadPaddingException e) {
      log.error("Error during AES encryption", e);
      throw new PantherEncryptionException("PantherException: " + e.getMessage());
    }
  }

  @Override
  public String decrypt(String wordToDecrypt) {
    try {
      Cipher cipher = getCipher();
      cipher.init(Cipher.DECRYPT_MODE, getSecretKey());
      byte[] encryptedBytes = Base64.getDecoder().decode(wordToDecrypt);
      byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
      return new String(decryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
             IllegalBlockSizeException | BadPaddingException e) {
      log.error("Error during AES decryption", e);
      throw new PantherDecryptionException("PantherException: " + e.getMessage());
    }
  }
}
