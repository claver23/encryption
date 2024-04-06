package com.etlions.encryption.infrastructure.web.openapi;

import com.etlions.encryption.application.input.AESDecryptUseCase;
import com.etlions.encryption.application.input.AESEncryptUseCase;
import com.etlions.encryption.application.input.AESGenerateKeyUseCase;
import com.etlions.encryption.application.input.AESLoadKeyUseCase;
import com.etlions.encryption.application.output.common.PantherSuccessRes;
import com.etlions.encryption.application.output.openapi.AESApiPort;

import com.etlions.encryption.infrastructure.web.openapi.mapper.AESKeyBitMapper;
import com.etlions.openapicodegen.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ServerWebExchange;

@RequiredArgsConstructor
@RequestMapping("/api/af")
@Controller
public class AESOpenApiAdapter implements AESApiPort {

  private final AESGenerateKeyUseCase aesGenerateKeyUseCase;
  private final AESEncryptUseCase aesEncryptUseCase;
  private final AESDecryptUseCase aesDecryptUseCase;
  private final AESLoadKeyUseCase aesLoadKeyUseCase;

  /**
   * @param decryptRequest
   * @return
   */
  @Override
  public ResponseEntity<SuccessResponse> aesDecryptPost(DecryptRequest decryptRequest) {
    return  new PantherSuccessRes()
        .dto(aesDecryptUseCase.AESDecrypt(decryptRequest.getWordToDecrypt()),
            "AES decryption executed successfully");
  }

  /**
   * @param encryptRequest
   * @param exchange
   * @return
   */
  @Override
  public ResponseEntity<SuccessResponse> aesEncryptPost(EncryptRequest encryptRequest) {
    return  new PantherSuccessRes()
        .dto(aesEncryptUseCase.AESEncrypt(encryptRequest.getWordToEncrypt()),
            "AES encryption executed successfully");
  }

  /**
   * @param generateKeyRequest
   * @param exchange
   * @return
   */
  @Override
  public ResponseEntity<SuccessResponse> aesGenerateKeyPost(GenerateKeyRequest generateKeyRequest) {
    return new PantherSuccessRes()
        .dto(aesGenerateKeyUseCase.AESGenerateKey(AESKeyBitMapper.mapToDtoEnum(generateKeyRequest.getKeyBits())
        ), "AES key generation successfully");
  }

  /**
   * @param loadKeyRequest
   * @param exchange
   * @return
   */
  @Override
  public ResponseEntity<SuccessResponse> aesLoadKeysPost(LoadKeyRequest loadKeyRequest) {
    return new PantherSuccessRes()
        .dto(aesLoadKeyUseCase.AESLoadKey(loadKeyRequest.getSecretKey()),
            "AES key loading executed successfully");
  }
}
