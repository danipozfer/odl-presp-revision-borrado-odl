package com.santalucia.cdc.config;

import com.santalucia.cdc.core.domain.EventoPresupuestoIndvDomain;
import com.santalucia.cdc.core.processors.HistPrespIndvProcessor;
import com.santalucia.cdc.core.processors.PrespIndvProcessor;
import com.santalucia.cdc.core.readers.HistPresupuestoIndvItemReader;
import com.santalucia.cdc.core.readers.PresupuestoIndvItemReader;
import com.santalucia.cdc.core.writers.HistPresupuestoIndItemWriter;
import com.santalucia.cdc.core.writers.PresupuestoIndItemWriter;
import com.santalucia.cdc.infrastructure.entity.Presupuestos;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StepHistoricPresIndv {

  public static final String STEP_NAME = "step-hist-col";
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Autowired
  private HistPresupuestoIndvItemReader presupuestoIndvItemReader;

  @Autowired
  private HistPrespIndvProcessor prespIndvProcessor;

  @Autowired
  private HistPresupuestoIndItemWriter presupuestoIndItemWriter;

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
