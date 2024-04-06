package com.etlions.encryption.domain.utils;

public interface PantherEncryption {

  String encrypt(String wordToEncrypt);

  String decrypt(String wordToDecrypt);
}
