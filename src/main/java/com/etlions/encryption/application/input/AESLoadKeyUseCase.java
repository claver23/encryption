package com.etlions.encryption.application.input;


import com.etlions.encryption.domain.model.PantherAES;

public interface AESLoadKeyUseCase {
  PantherAES AESLoadKey(String key);
}
