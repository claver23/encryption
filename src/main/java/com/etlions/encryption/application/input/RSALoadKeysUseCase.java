package com.etlions.encryption.application.input;

import com.etlions.encryption.domain.model.PantherRSA;

public interface RSALoadKeysUseCase {
  PantherRSA RSALoadKeysUseCase(String publicKey, String privateKey);
}
