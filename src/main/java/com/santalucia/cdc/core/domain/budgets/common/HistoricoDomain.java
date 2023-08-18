package com.santalucia.cdc.core.domain.budgets.common;
import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class HistoricoDomain {
  private TipoMDLDomain estadoPresupuesto;
  private Instant fecEstado;
  private TipoMDLDomain causaEstPresupuesto;
  private String numOrdenMovEstado;
  private String indBloqueoRevision;
}

