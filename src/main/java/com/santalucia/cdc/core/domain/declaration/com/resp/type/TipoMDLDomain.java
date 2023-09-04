package com.santalucia.cdc.core.domain.declaration.com.resp.type;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoMDLDomain {
  private String codOrigen;
  private String codMDL;
  private String descOrigen;
}
