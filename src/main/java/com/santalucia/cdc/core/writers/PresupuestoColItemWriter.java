package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PresupuestoColItemWriter implements ItemWriter<EventoPresupuestoColDomain> {

  Utils presupuestoColApiClient;

  EventoPresupuestoColDomain eventoPresupuestoColDomain;

  @Override
  public void write(List<? extends EventoPresupuestoColDomain> items) throws Exception {

    for (EventoPresupuestoColDomain presupuesto : items) {//debe actualizar el objeto entero?
      presupuestoColApiClient.actualizarPresupuesto(presupuesto.getIdMongo(), presupuesto);

      //se recorre el presupuesto y se va actualizando por partes, declaracion, objetos asegurados
    }
  }
}

