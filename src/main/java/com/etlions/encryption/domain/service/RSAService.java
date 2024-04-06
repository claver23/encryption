package com.etlions.encryption.domain.service;

import com.etlions.encryption.application.input.RSADecryptUseCase;
import com.etlions.encryption.application.input.RSAEncryptUseCase;
import com.etlions.encryption.application.input.RSAGenerateKeysUseCase;
import com.etlions.encryption.application.input.RSALoadKeysUseCase;

public interface RSAService extends RSAEncryptUseCase, RSADecryptUseCase, RSAGenerateKeysUseCase, RSALoadKeysUseCase {

}
