package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.DeclaracionClientService;
import com.santalucia.cdc.core.service.ObjetoAseguradoClientService;
import com.santalucia.cdc.core.service.PresupuestoColectivoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import org.springframework.batch.item.ItemWriter;

import java.util.List;
@SuppressWarnings("NullAway")
public class PresupuestoColItemWriter implements ItemWriter<EventoPresupuestoColDomain> {

  private final PresupuestoColectivoClientService presupuestoColectivoService;
  private final DeclaracionClientService declaracionService;
  private final ObjetoAseguradoClientService objetoAseguradoService;

  public PresupuestoColItemWriter(PresupuestosUtilsService utils,
                                  PresupuestoColectivoClientService presupuestoColectivoService,
                                  DeclaracionClientService declaracionService,
                                  ObjetoAseguradoClientService objetoAseguradoService) {
    this.presupuestoColectivoService = presupuestoColectivoService;
    this.declaracionService = declaracionService;
    this.objetoAseguradoService = objetoAseguradoService;
  }

  /**
   * Metodo para actualizar los presupuestos colectivos en ultima foto
   * @param items
   */
  @Override
  public void write(List<? extends EventoPresupuestoColDomain> items) {

    for (EventoPresupuestoColDomain budgets : items) {

      PresupuestoColectivoDomain budget = budgets.getPresupuestoColectivo();
      List<ObjetosAseguradosDomain> securedObjects = budgets.getObjetosAsegurados();
      List<DeclaracionDomain> declarations = budgets.getDeclaracion();
      presupuestoColectivoService.updateCollectiveBudget(budget, budget.getId());

      for (ObjetosAseguradosDomain securedObject: securedObjects) {
        objetoAseguradoService.updateSecuredObject(securedObject, securedObject.getId());
      }
      for (DeclaracionDomain declaration: declarations) {
        declaracionService.updateDeclaration(declaration, declaration.getId());
      }

    }
  }
}

