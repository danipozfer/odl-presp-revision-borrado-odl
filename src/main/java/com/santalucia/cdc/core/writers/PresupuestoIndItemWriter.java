package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PresupuestoIndItemWriter implements ItemWriter<EventoPresupuestoIndvDomain> {

  PresupuestoIndvApiClient presupuestoIndvApiClient;

  EventoPresupuestoIndvDomain eventoPresupuestoIndvDomain;
  @Override
  public void write(List<? extends EventoPresupuestoIndvDomain> items) throws Exception {

    for (EventoPresupuestoIndvDomain presupuesto : items) {

      presupuestoIndvApiClient.actualizarPresupuesto(presupuesto.getId(), presupuesto);

    }
  }

}
