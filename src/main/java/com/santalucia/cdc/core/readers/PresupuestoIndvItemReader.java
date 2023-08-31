package com.santalucia.cdc.core.readers;

import com.santalucia.arq.ams.batch.core.readers.PaginatedDataItemReader;
import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.individualBudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.mappers.budget.EventoPresupuestoColMapper;
import com.santalucia.cdc.core.mappers.budget.EventoPresupuestoIndvMapper;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@NoArgsConstructor
public class PresupuestoIndvItemReader extends PaginatedDataItemReader<EventoPresupuestoIndvDomain> {
  private final UtilsPresupuestos utils;
   EventoPresupuestoIndvMapper eventoPresupuestoIndvMapper;

  public PresupuestoIndvItemReader(UtilsPresupuestos utils, EventoPresupuestoIndvMapper eventoPresupuestoIndvMapper) {
    this.utils = utils;
    this.eventoPresupuestoIndvMapper = eventoPresupuestoIndvMapper;
  }

  /**
   * simplifica la lectura de los datos paginados de la fuente
   * @return
   */


  @Override
  protected Iterator<EventoPresupuestoIndvDomain> doPageRead() {
    List<PresupuestosResource> list = utils.getPresupuestos();
    List<PresupuestoIndividualDomain> list2 = convertToDomainList(list);
    List<EventoPresupuestoIndvDomain> result = new ArrayList<>();

    for (PresupuestoIndividualDomain pres : list2){
      EventoPresupuestoIndvDomain ev = new EventoPresupuestoIndvDomain();
      ev.setPresupuestoIndividual(pres);
      List<ObjetosAseguradosDomain> objs = apiObjectoAsegurado.getOBjecto(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setObjetosAsegurados(objs);
      List<DeclaracionDomain> declaracionDomains = apiDeclacion.getDeclaracion(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setDeclaracion(declaracionDomains);
      result.add(ev);
    }
    return list2.iterator();
  }

  private List<PresupuestoIndividualDomain> convertToDomainList(List<PresupuestosResource> list) {
    List<EventoPresupuestoColDomain> resultList = new ArrayList<>();
    for (PresupuestosResource resource : list) {
      PresupuestoColectivoDomain domain = eventoPresupuestoIndvMapper.toDomain(resource);
      resultList.add(domain);
    }
    return resultList;
  }
}
