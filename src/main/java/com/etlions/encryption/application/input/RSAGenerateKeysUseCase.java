package com.etlions.encryption.application.input;

import com.etlions.encryption.domain.utils.Constants;
import java.security.KeyPair;

public interface RSAGenerateKeysUseCase {
  KeyPair RSAGenerateKeyPair(Constants.RSAKeyBits bits);
}
