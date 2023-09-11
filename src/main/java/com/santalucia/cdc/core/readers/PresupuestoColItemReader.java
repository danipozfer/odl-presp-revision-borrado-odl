package com.santalucia.cdc.core.readers;

import com.santalucia.arq.ams.batch.core.readers.PaginatedDataItemReader;
import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PresupuestoColItemReader extends PaginatedDataItemReader<EventoPresupuestoColDomain> {


  private final PresupuestosUtilsService utils;
  private final PresupuestoColectivoClientService presupuestoApiClient;
  private final DeclaracionClientService declaracionApiClient;
  private final ObjetoAseguradoClientService objetoAseguradoApiClient;

  public PresupuestoColItemReader(PresupuestosUtilsService utils,
                                  PresupuestoColectivoClientService presupuestoApiClient,
                                  DeclaracionClientService declaracionApiClient,
                                  ObjetoAseguradoClientService objetoAseguradoApiClient) {
    this.utils = utils;
    this.presupuestoApiClient = presupuestoApiClient;
    this.declaracionApiClient = declaracionApiClient;
    this.objetoAseguradoApiClient = objetoAseguradoApiClient;
  }

  /**
   * simplifica la lectura de los datos paginados de la fuente
   * @return
   */
  @Override
  protected Iterator<EventoPresupuestoColDomain> doPageRead() {

    List<PresupuestoColectivoDomain> listColBudget = presupuestoApiClient.findCollectiveBudgets(null, "N");
    List<EventoPresupuestoColDomain> result = new ArrayList<>();

    for (PresupuestoColectivoDomain budget : listColBudget){
      EventoPresupuestoColDomain event = new EventoPresupuestoColDomain();
      event.setPresupuestoColectivo(budget);
      List<ObjetosAseguradosDomain> objects = new ArrayList<>();
      objects.add(objetoAseguradoApiClient.getSecuredObject(budget.getDatoIdentificativo().getIdPresupuestoODL()));
      event.setObjetosAsegurados(objects);
      List<DeclaracionDomain> declarations = declaracionApiClient.findDeclarationByIdPres(budget.getDatoIdentificativo().getIdPresupuestoODL());
      event.setDeclaracion(declarations);
      result.add(event);
    }
    return result.iterator();
  }

}
