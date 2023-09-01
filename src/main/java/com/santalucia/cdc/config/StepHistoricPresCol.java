package com.santalucia.cdc.config;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.processors.HistPrespColProcessor;
import com.santalucia.cdc.core.processors.PrespColProcessor;
import com.santalucia.cdc.core.readers.HistPresupuestoColItemReader;
import com.santalucia.cdc.core.readers.PresupuestoColItemReader;
import com.santalucia.cdc.core.writers.HistPresupuestoColItemWriter;
import com.santalucia.cdc.core.writers.PresupuestoColItemWriter;
import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepHistoricPresCol {

  public static final String STEP_NAME = "bbdd-to-file-step";
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private HistPresupuestoColItemReader presupuestoColItemReader;

  @Autowired
  private HistPrespColProcessor prespColProcessor;

  @Autowired
  private HistPresupuestoColItemWriter presupuestoColItemWriter;

  @Bean(STEP_NAME)
  public Step presupuestoStep() {
    return stepBuilderFactory.get("presupuestoStep")
      .<EventoPresupuestoColDomain, EventoPresupuestoColDomain>chunk(10)
      .reader(presupuestoColItemReader)
      .processor(prespColProcessor)
      .writer(presupuestoColItemWriter)
      .build();
  }
}
