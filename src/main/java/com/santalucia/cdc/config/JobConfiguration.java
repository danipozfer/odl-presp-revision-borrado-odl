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
   *
   * @param jobBuilderFactory
   * @param step1
   * @param step2
   * @param step3
   * @param step4
   * @param jobName
   * @return
   */

  @Bean
  public Job job(JobBuilderFactory jobBuilderFactory,
                 @Qualifier(StepPresCol.STEP_NAME) Step step1,
                 @Qualifier(StepPresIndv.STEP_NAME) Step step2,
                 @Qualifier(StepHistoricPresCol.STEP_NAME) Step step3,
                 @Qualifier(StepHistoricPresIndv.STEP_NAME) Step step4,

                 @Value("${spring.application.name}") String jobName) {
    return jobBuilderFactory
      .get(jobName)
      .incrementer(new RunIdIncrementer())
      .start(step1)
      .next(step2)
      .next(step3)
      .next(step4)
      .build();
  }


}
