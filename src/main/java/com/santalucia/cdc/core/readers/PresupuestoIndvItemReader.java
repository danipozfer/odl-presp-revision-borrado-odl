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
    List<PresupuestoIndividualDomain> list2 = presupuestoApiClient.findIndividualBudgets(null, "N");
    List<EventoPresupuestoIndvDomain> result = new ArrayList<>();

    for (PresupuestoIndividualDomain pres : list2){
      EventoPresupuestoIndvDomain ev = new EventoPresupuestoIndvDomain();
      ev.setPresupuestoIndividual(pres);
      List<ObjetosAseguradosDomain> objs = objetoAseguradoApiClient.findObjetoAseguradoByIdPres(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setObjetosAsegurados(objs);
      List<DeclaracionDomain> declaracionDomains = declaracionApiClient.findDeclarationByIdPres(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setDeclaracion(declaracionDomains);
      result.add(ev);
    }
    return result.iterator();
  }
}
