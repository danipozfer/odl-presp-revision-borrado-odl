package com.santalucia.cdc.config;

import com.santalucia.cdc.core.domain.EventoPresupuestoColDomain;
import com.santalucia.cdc.core.processors.PrespColProcessor;
import com.santalucia.cdc.core.readers.PresupuestoColItemReader;
import com.santalucia.cdc.core.writers.PresupuestoColItemWriter;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepPresCol {

  public static final String STEP_NAME = "step-col";
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private PresupuestoColItemReader presupuestoColItemReader;

  @Autowired
  private PrespColProcessor prespColProcessor;

  @Autowired
  private PresupuestoColItemWriter presupuestoColItemWriter;

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
