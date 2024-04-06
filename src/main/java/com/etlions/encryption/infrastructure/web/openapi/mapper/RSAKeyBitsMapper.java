package com.etlions.encryption.infrastructure.web.openapi.mapper;

import com.etlions.encryption.domain.utils.Constants;
import com.etlions.openapicodegen.dto.RSAKeyBits;

public class RSAKeyBitsMapper {

  public static RSAKeyBits mapToGeneratedEnum(Constants.RSAKeyBits originalEnum) {
    switch (originalEnum) {
      case BITS_512:
        return RSAKeyBits._512;
      case BITS_1024:
        return RSAKeyBits._1024;
      case BITS_2048:
        return RSAKeyBits._2048;
      case BITS_3072:
        return RSAKeyBits._3072;
      case BITS_4096:
        return RSAKeyBits._4096;
      default:
        throw new IllegalArgumentException("Unexpected value '" + originalEnum + "'");
    }
  }

  public static Constants.RSAKeyBits mapToDtoEnum(RSAKeyBits generatedEnum) {
    switch (generatedEnum) {
      case _512:
        return Constants.RSAKeyBits.BITS_512;
      case _1024:
        return Constants.RSAKeyBits.BITS_1024;
      case _2048:
        return Constants.RSAKeyBits.BITS_2048;
      case _3072:
        return Constants.RSAKeyBits.BITS_3072;
      case _4096:
        return Constants.RSAKeyBits.BITS_4096;
      default:
        throw new IllegalArgumentException("Unexpected value '" + generatedEnum + "'");
    }
  }
}
