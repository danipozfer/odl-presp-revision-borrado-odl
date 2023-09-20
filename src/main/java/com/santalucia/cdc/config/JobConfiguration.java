package com.santalucia.cdc.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import lombok.extern.slf4j.Slf4j;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;


import lombok.AllArgsConstructor;


@AllArgsConstructor
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(AppCustomFeaturesProperties.class)
@Slf4j
public class JobConfiguration {

  @Autowired
  private JobBuilderFactory jobBuilderFactory;
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  /**
   * @param jobBuilderFactory
   * @param stepPresCol
   * @param stepPresIndv
   * @param stepHistoricPresCol
   * @param stepHistoricPresIndv
   * @param jobName
   * @return
   */

  @Bean
  public Job job(JobBuilderFactory jobBuilderFactory,
                 @Qualifier(StepPresCol.STEP_NAME) Step stepPresCol,
                 @Qualifier(StepPresIndv.STEP_NAME) Step stepPresIndv,
                 @Qualifier(StepHistoricPresCol.STEP_NAME) Step stepHistoricPresCol,
                 @Qualifier(StepHistoricPresIndv.STEP_NAME) Step stepHistoricPresIndv,

                 @Value("${spring.application.name}") String jobName) {
    return jobBuilderFactory
      .get(jobName)
      .incrementer(new RunIdIncrementer())
      .start(stepPresCol)
      .next(stepPresIndv)
      .next(stepHistoricPresCol)
      .next(stepHistoricPresIndv)
      .build();
  }
}
