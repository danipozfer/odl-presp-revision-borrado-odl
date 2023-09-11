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

public class HistPresupuestoColItemReader extends PaginatedDataItemReader<EventoPresupuestoColDomain> {
  private final PresupuestosUtilsService utils;
  private final HistPresupuestoColectivoClientService presupuestoApiClient;
  private final HistDeclaracionClientService declaracionApiClient;
  private final HistObjetosAseguradosClientService objetoAseguradoApiClient;

  public HistPresupuestoColItemReader(PresupuestosUtilsService utils, HistPresupuestoColectivoClientService presupuestoApiClient,
                                      HistDeclaracionClientService declaracionApiClient,
                                      HistObjetosAseguradosClientService objetoAseguradoApiClient) {
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

    List<PresupuestoColectivoDomain> listHistColBudget = presupuestoApiClient.findAllHistoricCollectiveBudget("0001-01-01", "N");
    List<EventoPresupuestoColDomain> result = new ArrayList<>();

    for (PresupuestoColectivoDomain budget : listHistColBudget){
      EventoPresupuestoColDomain event = new EventoPresupuestoColDomain();
      event.setPresupuestoColectivo(budget);
      List<ObjetosAseguradosDomain> objects = objetoAseguradoApiClient.findAllHistoricSecuredObject(budget.getDatoIdentificativo().getIdPresupuestoODL());
      event.setObjetosAsegurados(objects);
      List<DeclaracionDomain> declarations = declaracionApiClient.findHistoricDeclarationByIdres(budget.getDatoIdentificativo().getIdPresupuestoODL());
      event.setDeclaracion(declarations);
      result.add(event);
    }
    return result.iterator();
  }
}
