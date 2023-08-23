package com.santalucia.cdc.core.readers;

import com.santalucia.arq.ams.batch.core.readers.PaginatedDataItemReader;
import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.common.securedObject.ObjetoAseguradoDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.mappers.EventoPresupuestoColMapper;
import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@NoArgsConstructor
public class PresupuestoColItemReader extends PaginatedDataItemReader<EventoPresupuestoColDomain> {


  UtilsPresupuestos utils;

  EventoPresupuestoColMapper eventoPresupuestoColMapper;

  public PresupuestoColItemReader(UtilsPresupuestos utils, EventoPresupuestoColMapper eventoPresupuestoColMapper) {
    this.utils = utils;
    this.eventoPresupuestoColMapper = eventoPresupuestoColMapper;
  }

  @Override
  protected Iterator<EventoPresupuestoColDomain> doPageRead() {
    List<PresupuestosResource> list = utils.getPresupuestos();
    List<PresupuestoColectivoDomain> list2 = convertToDomainList(list);
    List<EventoPresupuestoColDomain> result = new ArrayList<>();

    for (PresupuestoColectivoDomain pres : list2){
      EventoPresupuestoColDomain ev = new EventoPresupuestoColDomain();
      ev.setPresupuestoColectivo(pres);
      List<ObjetosAseguradosDomain> objs = apiObjectoAsegurado.getOBjecto(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setObjetosAsegurados(objs);
      List<DeclaracionDomain> declaracionDomains = apiDeclacion.getDeclaracion(pres.getDatoIdentificativo().getIdPresupuestoODL());
      ev.setDeclaracion(declaracionDomains);
      result.add(ev);
    }
    return list2.iterator();
  }

  private List<PresupuestoColectivoDomain> convertToDomainList(List<PresupuestosResource> list) {
    List<EventoPresupuestoColDomain> resultList = new ArrayList<>();
    for (PresupuestosResource resource : list) {
      PresupuestoColectivoDomain domain = eventoPresupuestoColMapper.toDomain(resource);
      resultList.add(domain);
    }
    return resultList;
  }
}
