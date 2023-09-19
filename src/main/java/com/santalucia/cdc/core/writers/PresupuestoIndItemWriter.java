package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.*;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
@SuppressWarnings("NullAway")
public class PresupuestoIndItemWriter implements ItemWriter<EventoPresupuestoIndvDomain> {

  private final PresupuestoIndividiualClientService presupuestoIndividualService;
  private final DeclaracionClientService declaracionService;
  private final ObjetoAseguradoClientService objetoAseguradoService;

  public PresupuestoIndItemWriter(PresupuestosUtilsService utils,
                                  PresupuestoIndividiualClientService presupuestoIndividualService,
                                  DeclaracionClientService declaracionService,
                                  ObjetoAseguradoClientService objetoAseguradoService) {
    this.presupuestoIndividualService = presupuestoIndividualService;
    this.declaracionService = declaracionService;
    this.objetoAseguradoService = objetoAseguradoService;
  }

  /**
   * Metodo para actualizar los presupuestos colectivos en ultima foto
   * @param items
   */
  @Override
  public void write(List<? extends EventoPresupuestoIndvDomain> items) {

    for (EventoPresupuestoIndvDomain budgets : items) {

      PresupuestoIndividualDomain budget = budgets.getPresupuestoIndividual();
      List<ObjetosAseguradosDomain> securedObjects = budgets.getObjetosAsegurados();
      List<DeclaracionDomain> declarations = budgets.getDeclaracion();
      presupuestoIndividualService.updateIndividualBudget(budget, budget.getId());

      for (ObjetosAseguradosDomain securedObject: securedObjects) {
        objetoAseguradoService.updateSecuredObject(securedObject, securedObject.getId());
      }
      for (DeclaracionDomain declaration: declarations) {
        declaracionService.updateDeclaration(declaration, declaration.getId());
      }

    }
  }

}
