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

    List<PresupuestoColectivoDomain> list2 = presupuestoApiClient.findCollectiveBudgets(null, "N");
    List<EventoPresupuestoColDomain> result = new ArrayList<>();

    for (PresupuestoColectivoDomain pres : list2){
      EventoPresupuestoColDomain ev = new EventoPresupuestoColDomain();
      ev.setPresupuestoColectivo(pres);
      List<ObjetosAseguradosDomain> objs = objetoAseguradoApiClient.findObjetoAseguradoByIdPres(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setObjetosAsegurados(objs);
      List<DeclaracionDomain> declaracionDomains = declaracionApiClient.findDeclarationByIdPres(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setDeclaracion(declaracionDomains);
      result.add(ev);
    }
    return result.iterator();
  }

}
