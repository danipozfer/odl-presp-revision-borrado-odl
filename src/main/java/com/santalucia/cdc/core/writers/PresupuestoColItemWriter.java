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

    for (EventoPresupuestoColDomain presupuesto : items) {
      presupuestoColApiClient.actualizarPresupuesto(presupuesto.getIdMongo(), presupuesto);

      presupuestoColApiClient.actualizarListaDeclaracion(presupuesto.getIdMongo(), presupuesto.getListaDeclaracion());

      presupuestoColApiClient.actualizarListaObjetosAsegurados(presupuesto.getIdMongo(), presupuesto.getListaObjetosAsegurados());
    }

      //se recorre el presupuesto y se va actualizando por partes, lista declaracion, lista objetos asegurados
    }
  }
}

