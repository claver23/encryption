package com.etlions.encryption.domain.utils;

public class Constants {

  public static final String AES_INSTANCE = "AES";
  public static final String RSA_INSTANCE = "RSA";
  public static final String UNSUPPORTED_OPERATION_FOR_ALGORITHM = "Operación no disponible para el algoritmo";
  public static final String COULD_NOT_ENCRYPT_KEY = "No se pudo encriptar, verificar accesos...";
  public static final String COULD_NOT_DECRYPT_KEY = "No se pudo desencriptar, verificar accesos...";

  private Constants() {
    // Private constructor to prevent instantiation
  }

  public enum RSAKeyBits {
    BITS_512(512),
    BITS_1024(1024),
    BITS_2048(2048),
    BITS_3072(3072),
    BITS_4096(4096);
    private final int bits;

    RSAKeyBits(int bits) {
      this.bits = bits;
    }

    public int getBits() {
      return bits;
    }
  }

  public enum AESKeyBits {
    BITS_128(128),
    BITS_192(192),
    BITS_256(256);
    private final int bits;

    AESKeyBits(int bits) {
      this.bits = bits;
    }

    public int getBits() {
      return bits;
    }
  }
}
