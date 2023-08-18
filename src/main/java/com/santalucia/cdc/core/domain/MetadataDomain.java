package com.santalucia.cdc.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class MetadataDomain {
  private String codVersion;
  private String txtCircuitoOrigen;
  private Instant fecSQL;
  private Long numOffsetMDL;
  private Long numOffsetCarga;
  private Instant fecCreacionRegistro;
  private String nomUsuarioCreacion;
  private String nomUsuarioModi;
  private Instant fecUltimaModi;
  private String hash;
  private String xRequestId;
}
