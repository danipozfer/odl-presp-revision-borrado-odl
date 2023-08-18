package com.santalucia.cdc.core.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class TipoMDLDomain {
  private String codOrigen;
  private String codMDL;
  private String descOrigen;
}
