package com.etlions.encryption.application.input;

import com.etlions.encryption.domain.utils.Constants;

import javax.crypto.SecretKey;

public interface AESGenerateKeyUseCase {

  SecretKey AESGenerateKey(Constants.AESKeyBits bitSize);
}
