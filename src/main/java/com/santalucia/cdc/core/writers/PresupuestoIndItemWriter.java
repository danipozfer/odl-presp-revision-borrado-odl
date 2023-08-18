package com.santalucia.cdc.core.writers;

import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PresupuestoIndItemWriter implements ItemWriter<Presupuestos> {
  @Override
  public void write(List<? extends Presupuestos> items) throws Exception {

    for (Presupuestos presupuesto : items) {

      presupuestoIndvApiClient.actualizarPresupuesto(presupuesto.getId(), presupuesto);

    }
  }

}
