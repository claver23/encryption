package com.etlions.encryption.domain.service.impl;


import com.etlions.encryption.domain.service.RSAService;
import com.etlions.encryption.domain.model.PantherRSA;
import com.etlions.encryption.domain.utils.Constants.RSAKeyBits;
import com.etlions.encryption.domain.utils.PantherEncryptionHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.*;
import java.security.*;

import static com.etlions.encryption.domain.utils.Constants.RSA_INSTANCE;

@Slf4j
@AllArgsConstructor
@Service
public class RSAServiceImpl implements RSAService {

  private static final String PUBLIC_KEY_FILE_PATH = "rsa-public-key.der";
  private static final String PRIVATE_KEY_FILE_PATH = "rsa-private-key.der";

  private PantherRSA pantherRSA;
  private PublicKey publicKey;
  private PrivateKey privateKey;

  public RSAServiceImpl() throws NoSuchAlgorithmException {
    loadOrGenerateKeys();
  }

  @Override
  public PantherRSA RSALoadKeysUseCase(String publicKey, String privateKey) {
    try {
      PantherEncryptionHelper peh = new PantherEncryptionHelper();
      this.pantherRSA = new PantherRSA(
          peh.privateKeyFromString(privateKey),
          peh.publicKeyFromString(publicKey));
      return this.pantherRSA;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public KeyPair RSAGenerateKeyPair(RSAKeyBits bits) {
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_INSTANCE);
      keyPairGenerator.initialize(bits.getBits());
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      return keyPair;
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String RSADecrypt(String wordToDecrypt) {
    return pantherRSA.decrypt(wordToDecrypt);
  }

  @Override
  public String RSAEncrypt(String wordToEncrypt) {
    return pantherRSA.encrypt(wordToEncrypt);
  }

  private void loadOrGenerateKeys() {
    File publicKeyFile = new File(PUBLIC_KEY_FILE_PATH);
    File privateKeyFile = new File(PRIVATE_KEY_FILE_PATH);

    if (publicKeyFile.exists() && privateKeyFile.exists()) {
      loadKeys();
    } else {
      generateAndSaveKeys(publicKeyFile, privateKeyFile);
    }
  }

  private void loadKeys() {
    try (ObjectInputStream oisPublicKey = new
        ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE_PATH));
         ObjectInputStream oisPrivateKey = new
             ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE_PATH))) {
      publicKey = (PublicKey) oisPublicKey.readObject();
      privateKey = (PrivateKey) oisPrivateKey.readObject();
      this.pantherRSA = new PantherRSA(privateKey, publicKey);
    } catch (IOException | ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  private void generateAndSaveKeys(File publicKeyFile, File privateKeyFile) {
    try {
      KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA_INSTANCE);
      keyPairGenerator.initialize(2048);
      KeyPair keyPair = keyPairGenerator.generateKeyPair();

      publicKey = keyPair.getPublic();
      privateKey = keyPair.getPrivate();
      this.pantherRSA = new PantherRSA(privateKey, publicKey);

      try (ObjectOutputStream publicOos = new
          ObjectOutputStream(new FileOutputStream(publicKeyFile));
           ObjectOutputStream privateOos = new
               ObjectOutputStream(new FileOutputStream(privateKeyFile))) {

        publicOos.writeObject(publicKey);
        privateOos.writeObject(privateKey);

      } catch (IOException e) {
        e.printStackTrace();
      }
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

}
