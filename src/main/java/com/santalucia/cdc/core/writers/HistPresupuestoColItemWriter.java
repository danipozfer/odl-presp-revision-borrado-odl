package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.DeclaracionClientService;
import com.santalucia.cdc.core.service.ObjetoAseguradoClientService;
import com.santalucia.cdc.core.service.PresupuestoColectivoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.UUID;

public class HistPresupuestoColItemWriter implements ItemWriter<EventoPresupuestoColDomain> {

  private final PresupuestosUtilsService utils;
  private final PresupuestoColectivoClientService presupuestoColectivoService;
  private final DeclaracionClientService declaracionService;
  private final ObjetoAseguradoClientService objetoAseguradoService;

  public HistPresupuestoColItemWriter(PresupuestosUtilsService utils,
                                  PresupuestoColectivoClientService presupuestoColectivoService,
                                  DeclaracionClientService declaracionService,
                                  ObjetoAseguradoClientService objetoAseguradoService) {
    this.utils = utils;
    this.presupuestoColectivoService = presupuestoColectivoService;
    this.declaracionService = declaracionService;
    this.objetoAseguradoService = objetoAseguradoService;
  }

  @Override
  public void write(List<? extends EventoPresupuestoColDomain> items) throws Exception {

    for (EventoPresupuestoColDomain historicBudgets : items) {
      UUID uuid = utils.getOrSetUUID(null);

      PresupuestoColectivoDomain historicBudget = historicBudgets.getPresupuestoColectivo();
      List<ObjetosAseguradosDomain> historicSecuredObjects = historicBudgets.getObjetosAsegurados();
      List<DeclaracionDomain> historicDeclarations = historicBudgets.getDeclaracion();
      presupuestoColectivoService.updateHistCollectiveBudget(historicBudget, historicBudget.getId(), uuid);

      for (ObjetosAseguradosDomain historicSecuredObject: historicSecuredObjects) {
        objetoAseguradoService.updateSecuredObject(historicSecuredObject, historicSecuredObject.getId(), uuid);
      }
      for (DeclaracionDomain historicDeclaration: historicDeclarations) {
        declaracionService.updateDeclaration(historicDeclaration, historicDeclaration.getId(), uuid);
      }

    }
  }
}

