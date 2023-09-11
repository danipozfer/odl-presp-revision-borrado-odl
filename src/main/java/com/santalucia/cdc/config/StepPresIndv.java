package com.santalucia.cdc.config;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.processors.PrespColProcessor;
import com.santalucia.cdc.core.processors.PrespIndvProcessor;
import com.santalucia.cdc.core.readers.PresupuestoColItemReader;
import com.santalucia.cdc.core.readers.PresupuestoIndvItemReader;
import com.santalucia.cdc.core.writers.PresupuestoColItemWriter;
import com.santalucia.cdc.core.writers.PresupuestoIndItemWriter;
import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepPresIndv {

  public static final String STEP_NAME = "step-ind";
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private PresupuestoIndvItemReader presupuestoIndvItemReader;

  @Autowired
  private PrespIndvProcessor prespIndvProcessor;

  @Autowired
  private PresupuestoIndItemWriter presupuestoIndItemWriter;

  @Bean(STEP_NAME)
  public Step presupuestoStep() {
    return stepBuilderFactory.get("presupuestoStep")
      .<EventoPresupuestoIndvDomain, EventoPresupuestoIndvDomain>chunk(10)
      .reader(presupuestoIndvItemReader)
      .processor(prespIndvProcessor)
      .writer(presupuestoIndItemWriter)
      .build();
  }
}
