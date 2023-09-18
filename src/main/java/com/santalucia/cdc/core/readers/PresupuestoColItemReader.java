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
   * Simplifica la lectura de los datos paginados de la fuente
   * Se encarga de obtener presupuestos colectivos en ultima foto
   * no anonimizados ni convertidos en polizas
   *
   * @return
   */
  @Override
  protected Iterator<EventoPresupuestoColDomain> doPageRead() {
    List<PresupuestoColectivoDomain> budgets = presupuestoApiClient.findCollectiveBudgets("N", "N", "0");//preguntar
    List<EventoPresupuestoColDomain> result = new ArrayList<>();

    for (PresupuestoColectivoDomain budget: budgets) {
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
