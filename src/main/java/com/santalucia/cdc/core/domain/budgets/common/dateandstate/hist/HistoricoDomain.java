package com.santalucia.cdc.core.domain.budgets.common.dateandstate.hist;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoricoDomain {
  private TipoMDLDomain estadoPresupuesto;
  private Instant fecEstado;
  private TipoMDLDomain causaEstPresupuesto;
  private String numOrdenMovEstado;
  private String indBloqueoRevision;
}

