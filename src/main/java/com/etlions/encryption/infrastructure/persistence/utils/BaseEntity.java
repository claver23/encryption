package com.etlions.encryption.infrastructure.persistence.utils;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {

//  @Id
  private String id;
//  @CreatedDate
  private LocalDateTime createdAt;
//  @LastModifiedDate
  private LocalDateTime updatedAt;

  private Boolean isActive;
}
