package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.*;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.UUID;

public class HistPresupuestoIndItemWriter implements ItemWriter<EventoPresupuestoIndvDomain> {

  private final PresupuestosUtilsService utils;
  private final HistPresupuestoIndividualClientService presupuestoIndividualService;
  private final DeclaracionClientService declaracionService;
  private final ObjetoAseguradoClientService objetoAseguradoService;

  public HistPresupuestoIndItemWriter(PresupuestosUtilsService utils,
                                      HistPresupuestoIndividualClientService presupuestoIndividualService,
                                      DeclaracionClientService declaracionService,
                                      ObjetoAseguradoClientService objetoAseguradoService) {
    this.utils = utils;
    this.presupuestoIndividualService = presupuestoIndividualService;
    this.declaracionService = declaracionService;
    this.objetoAseguradoService = objetoAseguradoService;
  }

  @Override
  public void write(List<? extends EventoPresupuestoIndvDomain> items) {

    for (EventoPresupuestoIndvDomain budgets : items) {
      UUID uuid = utils.getOrSetUUID(null);

      PresupuestoIndividualDomain historicBudget = budgets.getPresupuestoIndividual();
      List<ObjetosAseguradosDomain> securedObjects = budgets.getObjetosAsegurados();
      List<DeclaracionDomain> declarations = budgets.getDeclaracion();
      presupuestoIndividualService.updateHistIndividualBudget(historicBudget, historicBudget.getId(), uuid);

      for (ObjetosAseguradosDomain securedObject: securedObjects) {
        objetoAseguradoService.updateSecuredObject(securedObject, securedObject.getId(), uuid);
      }
      for (DeclaracionDomain declaration: declarations) {
        declaracionService.updateDeclaration(declaration, declaration.getId(), uuid);
      }

    }
  }
}
