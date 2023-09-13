package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.*;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
import java.util.UUID;

public class HistPresupuestoColItemWriter implements ItemWriter<EventoPresupuestoColDomain> {

  private final PresupuestosUtilsService utils;
  private final HistPresupuestoColectivoClientService presupuestoColectivoService;
  private final DeclaracionClientService declaracionService;
  private final ObjetoAseguradoClientService objetoAseguradoService;



  public HistPresupuestoColItemWriter(PresupuestosUtilsService utils,
                                      HistPresupuestoColectivoClientService presupuestoColectivoService,
                                  DeclaracionClientService declaracionService,
                                  ObjetoAseguradoClientService objetoAseguradoService) {
    this.utils = utils;
    this.presupuestoColectivoService = presupuestoColectivoService;
    this.declaracionService = declaracionService;
    this.objetoAseguradoService = objetoAseguradoService;
  }

  /**
   * Metodo para actualizar los presupuestos colectivos en historico
   * @param items
   */
  @Override
  public void write(List<? extends EventoPresupuestoColDomain> items) {

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

