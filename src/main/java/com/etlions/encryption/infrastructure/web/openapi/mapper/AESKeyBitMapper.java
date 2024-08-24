package com.etlions.encryption.infrastructure.web.openapi.mapper;

import com.etlions.encryption.domain.utils.Constants;
import com.etlions.openapicodegen.dto.AESKeyBits;
// potential use of framework mapper with @'s
public class AESKeyBitMapper {

  public static AESKeyBits mapToGeneratedEnum(Constants.AESKeyBits originalEnum) {
    switch (originalEnum) {
      case BITS_128:
        return AESKeyBits._128;
      case BITS_192:
        return AESKeyBits._192;
      case BITS_256:
        return AESKeyBits._256;
      default:
        throw new IllegalArgumentException("Unexpected value '" + originalEnum + "'");
    }
  }

  public static Constants.AESKeyBits mapToDtoEnum(AESKeyBits generatedEnum) {
    switch (generatedEnum) {
      case _128:
        return Constants.AESKeyBits.BITS_128;
      case _192:
        return Constants.AESKeyBits.BITS_192;
      case _256:
        return Constants.AESKeyBits.BITS_256;
      default:
        throw new IllegalArgumentException("Unexpected value '" + generatedEnum + "'");
    }
  }
}
