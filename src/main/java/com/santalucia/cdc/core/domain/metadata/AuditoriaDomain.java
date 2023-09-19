package com.santalucia.cdc.core.domain.metadata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditoriaDomain {

  private Instant createdDate;

  private Instant lastModifiedDate;

  private String createdByUser;

  private String lastModifiedByUser;
}
