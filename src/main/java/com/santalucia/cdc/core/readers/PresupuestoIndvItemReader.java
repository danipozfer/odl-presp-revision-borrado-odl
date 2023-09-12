package com.santalucia.cdc.core.readers;

import com.santalucia.arq.ams.batch.core.readers.PaginatedDataItemReader;
import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.service.DeclaracionClientService;
import com.santalucia.cdc.core.service.ObjetoAseguradoClientService;
import com.santalucia.cdc.core.service.PresupuestoIndividiualClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class PresupuestoIndvItemReader extends PaginatedDataItemReader<EventoPresupuestoIndvDomain> {
  private final PresupuestosUtilsService utils;
  private final PresupuestoIndividiualClientService presupuestoApiClient;
  private final DeclaracionClientService declaracionApiClient;
  private final ObjetoAseguradoClientService objetoAseguradoApiClient;

  public PresupuestoIndvItemReader(PresupuestosUtilsService utils,
                                   PresupuestoIndividiualClientService presupuestoApiClient,
                                   DeclaracionClientService declaracionApiClient,
                                   ObjetoAseguradoClientService objetoAseguradoApiClient1) {
    this.utils = utils;
    this.presupuestoApiClient = presupuestoApiClient;
    this.declaracionApiClient = declaracionApiClient;
    this.objetoAseguradoApiClient = objetoAseguradoApiClient1;
  }

  /**
   * simplifica la lectura de los datos paginados de la fuente
   * @return
   */
  @Override
  protected Iterator<EventoPresupuestoIndvDomain> doPageRead() {
    PresupuestoIndividualDomain budget = presupuestoApiClient.getIndividualBudget(null, "N");
    List<EventoPresupuestoIndvDomain> result = new ArrayList<>();

    EventoPresupuestoIndvDomain event = new EventoPresupuestoIndvDomain();
    event.setPresupuestoIndividual(budget);
    List<ObjetosAseguradosDomain> objects = new ArrayList<>();
    objects.add(objetoAseguradoApiClient.getSecuredObject(budget.getDatoIdentificativo().getIdPresupuestoODL()));
    event.setObjetosAsegurados(objects);
    List<DeclaracionDomain> declarations = declaracionApiClient.findDeclarationByIdPres(budget.getDatoIdentificativo().getIdPresupuestoODL());
    event.setDeclaracion(declarations);
    result.add(event);

    return result.iterator();
  }
}
