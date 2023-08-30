package com.santalucia.cdc.core.readers;

import com.santalucia.arq.ams.batch.core.readers.PaginatedDataItemReader;
import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.mappers.budget.EventoPresupuestoIndvMapper;
import lombok.NoArgsConstructor;

import java.util.Iterator;
import java.util.List;
@NoArgsConstructor
public class PresupuestoIndvItemReader extends PaginatedDataItemReader<EventoPresupuestoIndvDomain> {
  private final UtilsPresupuestos utils;
   EventoPresupuestoIndvMapper eventoPresupuestoIndvMapper;

  /**
   * simplifica la lectura de los datos paginados de la fuente
   * @return
   */
  @Override //Realizará las peticiones a la api
  protected Iterator<EventoPresupuestoIndvDomain> doPageRead() {

    List<PresupuestosResource> list = utils.getPresupuestos();
    List<EventoPresupuestoIndvDomain> list2 = list.foreach(a -> eventoPresupuestoIndvMapper.toDomain(a));
    return list2.iterator();
  }
}
