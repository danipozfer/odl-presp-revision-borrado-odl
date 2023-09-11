package com.santalucia.cdc.core.readers;

import com.santalucia.arq.ams.batch.core.readers.PaginatedDataItemReader;
import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class HistPresupuestoIndvItemReader extends PaginatedDataItemReader<EventoPresupuestoIndvDomain> {
  private final PresupuestosUtilsService utils;
  private final HistPresupuestoIndividualClientService presupuestoApiClient;
  private final DeclaracionClientService declaracionApiClient;
  private final ObjetoAseguradoClientService objetoAseguradoApiClient;

  public HistPresupuestoIndvItemReader(PresupuestosUtilsService utils,
                                       HistPresupuestoIndividualClientService presupuestoApiClient,
                                       DeclaracionClientService declaracionApiClient,
                                       ObjetoAseguradoClientService objetoAseguradoApiClient1) {
    this.utils = utils;
    this.presupuestoApiClient = presupuestoApiClient;
    this.declaracionApiClient = declaracionApiClient;
    this.objetoAseguradoApiClient = objetoAseguradoApiClient1;
  }


  /**
   * simplifica la lectura de los datos paginados de la fuente
   *
   * @return
   */
  @Override
  protected Iterator<EventoPresupuestoIndvDomain> doPageRead() {
    List<PresupuestoIndividualDomain> listHistIndvBudget = presupuestoApiClient.findAllHistoricIndividualBudget(null, "N");
    List<EventoPresupuestoIndvDomain> result = new ArrayList<>();

    for (PresupuestoIndividualDomain budget : listHistIndvBudget) {
      EventoPresupuestoIndvDomain event = new EventoPresupuestoIndvDomain();
      event.setPresupuestoIndividual(budget);
      List<ObjetosAseguradosDomain> objects = objetoAseguradoApiClient.findAllHistoricSecuredObject(budget.getDatoIdentificativo().getIdPresupuestoODL());
      event.setObjetosAsegurados(objects);
      List<DeclaracionDomain> declarations = declaracionApiClient.findHistoricDeclarationByIdres(budget.getDatoIdentificativo().getIdPresupuestoODL());
      event.setDeclaracion(declarations);
      result.add(event);
    }
    return result.iterator();
  }
}
