package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.*;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
@SuppressWarnings("NullAway")
public class HistPresupuestoIndItemWriter implements ItemWriter<EventoPresupuestoIndvDomain> {

  private final HistPresupuestoIndividualClientService presupuestoIndividualService;
  private final DeclaracionClientService declaracionService;
  private final ObjetoAseguradoClientService objetoAseguradoService;

  public HistPresupuestoIndItemWriter(HistPresupuestoIndividualClientService presupuestoIndividualService,
                                      DeclaracionClientService declaracionService,
                                      ObjetoAseguradoClientService objetoAseguradoService) {
    this.presupuestoIndividualService = presupuestoIndividualService;
    this.declaracionService = declaracionService;
    this.objetoAseguradoService = objetoAseguradoService;
  }

  /**
   * Metodo para actualizar los presupuestos individuales en historico
   * @param items
   */
  @Override
  public void write(List<? extends EventoPresupuestoIndvDomain> items) {

    for (EventoPresupuestoIndvDomain budgets : items) {

      PresupuestoIndividualDomain historicBudget = budgets.getPresupuestoIndividual();
      List<ObjetosAseguradosDomain> securedObjects = budgets.getObjetosAsegurados();
      List<DeclaracionDomain> declarations = budgets.getDeclaracion();
      presupuestoIndividualService.updateHistIndividualBudget(historicBudget, historicBudget.getId());

      for (ObjetosAseguradosDomain securedObject: securedObjects) {
        objetoAseguradoService.updateSecuredObject(securedObject, securedObject.getId());
      }
      for (DeclaracionDomain declaration: declarations) {
        declaracionService.updateDeclaration(declaration, declaration.getId());
      }

    }
  }
}
