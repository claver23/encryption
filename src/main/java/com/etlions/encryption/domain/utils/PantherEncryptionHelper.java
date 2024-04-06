package com.etlions.encryption.domain.utils;

import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;


@NoArgsConstructor
public class PantherEncryptionHelper {

  public PublicKey publicKeyFromString(String publicKeyPEM) throws Exception {
    byte[] encoded = Base64.getDecoder().decode(publicKeyPEM);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(encoded);
    return keyFactory.generatePublic(keySpec);
  }

  public PrivateKey privateKeyFromString(String privateKeyPEM) throws Exception {
    byte[] encoded = Base64.getDecoder().decode(privateKeyPEM);
    KeyFactory keyFactory = KeyFactory.getInstance("RSA");
    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(encoded);
    return keyFactory.generatePrivate(keySpec);
  }

  public SecretKey secretKeyFromString(String key) {
    // Assuming the key is encoded using Base64
    byte[] decodedKey = Base64.getDecoder().decode(key);
    return new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
  }
}
