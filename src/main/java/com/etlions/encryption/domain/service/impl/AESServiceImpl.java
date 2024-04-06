package com.etlions.encryption.domain.service.impl;

import com.etlions.encryption.domain.model.PantherAES;
import com.etlions.encryption.domain.service.AESService;
import com.etlions.encryption.domain.utils.Constants;
import com.etlions.encryption.domain.utils.PantherEncryptionHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.*;

@Slf4j
@AllArgsConstructor
@Service
public class AESServiceImpl implements AESService {
  private static final String AES_KEY_FILE_PATH = "aes-secret-key.ser";
  private PantherAES pantherAES;

  private SecretKey secretKey;

  public AESServiceImpl() {
    loadOrGenerateKey();
  }

  @Override
  public String AESDecrypt(String wordToDecrypt) {
    return pantherAES.decrypt(wordToDecrypt);
  }

  @Override
  public String AESEncrypt(String wordToEncrypt) {
    return pantherAES.encrypt(wordToEncrypt);
  }

  @Override
  public SecretKey AESGenerateKey(Constants.AESKeyBits bitSize) {
    try {
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(bitSize.getBits());
      return keyGenerator.generateKey();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
  
  @Override
  public PantherAES AESLoadKey(String key) {
    PantherEncryptionHelper peh = new PantherEncryptionHelper();
    this.pantherAES = new PantherAES(peh.secretKeyFromString(key));
    return this.pantherAES;
  }

  public void loadKey() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(AES_KEY_FILE_PATH))) {
      secretKey = (SecretKey) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException("Failed to load AES key from file", e);
    }
  }

  private void loadOrGenerateKey() {
    File keyFile = new File(AES_KEY_FILE_PATH);
    if (keyFile.exists()) {
      loadKey();
    } else {
      SecretKey generatedKey = AESGenerateKey(Constants.AESKeyBits.BITS_256); // You can choose the appropriate bit size
      pantherAES = new PantherAES(generatedKey);
      saveKey(generatedKey, AES_KEY_FILE_PATH);
    }
  }

  private void saveKey(SecretKey generatedKey, String keyFilePath) {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(keyFilePath))) {
      oos.writeObject(generatedKey);
    } catch (IOException e) {
      throw new RuntimeException("Failed to save AES key to file", e);
    }
  }

}
