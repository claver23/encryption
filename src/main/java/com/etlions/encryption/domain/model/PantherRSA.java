package com.etlions.encryption.domain.model;

import com.etlions.encryption.domain.exception.PantherDecryptionException;
import com.etlions.encryption.domain.exception.PantherEncryptionException;
import com.etlions.encryption.domain.utils.PantherEncryptionAlgorithm;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;

import static com.etlions.encryption.domain.utils.Constants.RSA_INSTANCE;

@Slf4j
@RequiredArgsConstructor
public class PantherRSA extends PantherEncryptionAlgorithm {
  protected PrivateKey privateKey;
  protected PublicKey publicKey;
  protected KeyPair keyPair;
  @Getter
  private Boolean isLoaded;

  public PantherRSA(KeyPair keyPair) {
    this.keyPair = keyPair;
    this.publicKey = keyPair.getPublic();
    this.privateKey = keyPair.getPrivate();
    this.isLoaded = true;
  }

  public PantherRSA(PrivateKey privateKey, PublicKey publicKey) {
    keyPair = new KeyPair(publicKey, privateKey);
    this.privateKey = privateKey;
    this.publicKey = publicKey;
    this.isLoaded = true;
  }

  @JsonIgnore
  @Override
  public Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
    return Cipher.getInstance(RSA_INSTANCE);
  }

  @Override
  protected PublicKey getPublicKey() throws InvalidKeySpecException {
    return this.publicKey;
  }

  @Override
  protected PrivateKey getPrivateKey() throws InvalidKeySpecException {
    return this.privateKey;
  }

  @Override
  public String encrypt(String wordToEncrypt) {
    try {
      Cipher cipher = getCipher();
      cipher.init(Cipher.ENCRYPT_MODE, getPublicKey());
      byte[] encryptedBytes = cipher.doFinal(wordToEncrypt.getBytes());
      return java.util.Base64.getEncoder().encodeToString(encryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
             BadPaddingException | InvalidKeySpecException e) {
      log.error("Error during RSA encryption", e);
      throw new PantherEncryptionException("PantherException : " + e.getMessage());
    }
  }

  @Override
  public String decrypt(String wordToDecrypt) {
    try {
      Cipher cipher = getCipher();
      cipher.init(Cipher.DECRYPT_MODE, getPrivateKey());
      byte[] encryptedBytes = java.util.Base64.getDecoder().decode(wordToDecrypt);
      byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
      return new String(decryptedBytes);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException |
             BadPaddingException | InvalidKeySpecException e) {
      log.error("Error during RSA decryption", e);
      throw new PantherDecryptionException("PantherException : " + e.getMessage());
    }
  }
}


