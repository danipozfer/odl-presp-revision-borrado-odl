package com.santalucia.cdc.core.domain.securedobjects.pricing;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatoIdentifTarifDomain {
  private TipoMDLDomain tipoComplemento;
  private TipoMDLDomain categoria;
  private TipoMDLDomain agrupGarantia;
  private String numOrdenAgrupGar;
  private String agrupGarantiaHog;
  private TipoMDLDomain garantiaUnitaria;
  private String numOrdenPersonaRol;
  private TipoMDLDomain riesgo;
  private String edadTarificacion;
  private String codAplicacion;
  private String numOrdenComplemento;
}
