package com.santalucia.cdc.core.domain;

import com.santalucia.cdc.core.domain.budgets.individualBudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class EventoPresupuestoIndvDomain {

  private String indTipoEvento;
  private PresupuestoIndividualDomain presupuestoIndividual;
  private List<ObjetosAseguradosDomain> objetosAsegurados;
  private List<DeclaracionDomain> declaracion;
}
