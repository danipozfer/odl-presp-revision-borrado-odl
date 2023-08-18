package com.santalucia.cdc.core.processors;

import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.item.ItemProcessor;

public class PrespIndvProcessor implements ItemProcessor<Presupuestos,Presupuestos> {
  @Override
  public Presupuestos process(Presupuestos presupuestos) throws Exception {
    return presupuestos;
  }
}
