package com.etlions.encryption.domain.service;

import com.etlions.encryption.application.input.AESDecryptUseCase;
import com.etlions.encryption.application.input.AESEncryptUseCase;
import com.etlions.encryption.application.input.AESGenerateKeyUseCase;
import com.etlions.encryption.application.input.AESLoadKeyUseCase;

public interface AESService extends AESDecryptUseCase, AESEncryptUseCase, AESGenerateKeyUseCase, AESLoadKeyUseCase {
}
