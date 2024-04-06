package com.etlions.encryption.infrastructure.web.openapi;

import com.etlions.encryption.application.input.RSADecryptUseCase;
import com.etlions.encryption.application.input.RSAEncryptUseCase;
import com.etlions.encryption.application.input.RSAGenerateKeysUseCase;
import com.etlions.encryption.application.input.RSALoadKeysUseCase;
import com.etlions.encryption.application.output.common.PantherSuccessRes;
import com.etlions.encryption.application.output.openapi.RSAApiPort;
import com.etlions.encryption.infrastructure.web.openapi.mapper.RSAKeyBitsMapper;
import com.etlions.openapicodegen.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/api/af")
@Controller
public class RSAOpenApiAdapter implements RSAApiPort {

  private final RSAGenerateKeysUseCase rsaGenerateKeysUseCase;
  private final RSADecryptUseCase rsaDecryptUseCase;
  private final RSAEncryptUseCase rsaEncryptUseCase;
  private final RSALoadKeysUseCase rsaLoadKeysUseCase;

  @Override
  public ResponseEntity<SuccessResponse> rsaDecryptPost(DecryptRequest decryptRequest) {
    return  new PantherSuccessRes()
        .dto(rsaDecryptUseCase.RSADecrypt(decryptRequest.getWordToDecrypt()),
            "RSA decryption executed successfully");
  }

  @Override
  public ResponseEntity<SuccessResponse> rsaEncryptPost(EncryptRequest encryptRequest) {
    return new PantherSuccessRes()
        .dto(rsaEncryptUseCase.RSAEncrypt(encryptRequest.getWordToEncrypt()),
            "RSA encryption executed successfully");
  }

  @Override
  public ResponseEntity<SuccessResponse> rsaGenerateKeysPost(GenerateKeysRequest generateKeysRequest) {
    return  new PantherSuccessRes()
        .dto(rsaGenerateKeysUseCase.RSAGenerateKeyPair(RSAKeyBitsMapper.mapToDtoEnum(generateKeysRequest.getKeyBits())),
            "RSA key generation executed successfully");
  }

  @Override
  public ResponseEntity<SuccessResponse> rsaLoadKeysPost(LoadKeysRequest loadKeysRequest) {
    return new PantherSuccessRes()
        .dto(rsaLoadKeysUseCase
                .RSALoadKeysUseCase(
                        loadKeysRequest.getPublicKey(),
                        loadKeysRequest.getPrivateKey()),
            "RSA keys loading executed successfully");
  }
}
