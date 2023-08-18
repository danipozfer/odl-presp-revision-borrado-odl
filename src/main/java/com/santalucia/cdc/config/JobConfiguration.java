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


  /* *//** Nombre de step que extrae datos y los carga a fichero *//*
  private static final String STEP_DATA_EXTRACTION_NAME = "personas-to-file";

  *//** nombre del step que consulta recibos atraves del cliente y los mustra por consola *//*
  private static final String STEP_FEIGN_RECIBOS_NAME = "recibos-to-console";

  private static final String PERSONAS_STEP = "persona-step";
  private static final String PERSONAS_ITEM_READER = "PersonasMybatisReader";
  private static final String PERSONAS_ITEM_WRITER = "PersonasWriter";

  private static final String RECIBOS_STEP = "recibos-step";
  private static final String RECIBOS_ITEM_READER = "recibosReader";
  private static final String RECIBOS_ITEM_WRITER = "recibosWriter";
  private static final String PERSONAS_PROCESSOR = "PersonasProcessor";

  private final AppCustomFeaturesProperties properties;
  private final JobBuilderFactory jobBuilderFactory;
  private final StepBuilderFactory stepBuilderFactory;
  private final PersonasMapper personasMapper;

  *//**
   * Job Bean
   *
   * @param step
   * @param step
   *//*
  @Bean
  public Job job(
    @Qualifier(PERSONAS_STEP) Step step, @Qualifier(RECIBOS_STEP) Step recibosStep,
    @Value("${spring.application.name}") String jobName) {
    return jobBuilderFactory
      .get(jobName)
      .incrementer(new RunIdIncrementer())
      .start(step)
      .next(recibosStep)
      .build();
  }

  *//**
   * Step bean
   *
   * @param reader
   * @param processor
   * @param writer
   *//*
  @Bean(PERSONAS_STEP)
  public Step step(
    @Qualifier(PERSONAS_ITEM_READER) ItemReader<Personas> reader,
    @Qualifier(PERSONAS_PROCESSOR) ItemProcessor<Personas, PersonaDomain> processor,
    @Qualifier(PERSONAS_ITEM_WRITER) ItemWriter<PersonaDomain> writer) {
    return stepBuilderFactory
      .get(STEP_DATA_EXTRACTION_NAME)
      .<Personas, PersonaDomain>chunk(properties.getChunkSize())
      .reader(reader)
      .processor(processor)
      .writer(writer)
      .build();
  }

  *//**
   * MyBatisCursorItemReader<Personas> Bean
   *
   * @param sqlSessionFactory
   *//*
  @Bean(PERSONAS_ITEM_READER)
  public MyBatisCursorItemReader<Personas> getPersonasMyBatisReader(
    @Qualifier(MyBatisProperties.PRIMARY_SESSION_FACTORY) SqlSessionFactory sqlSessionFactory) {

    return PersonaMybatisItemReader.getPersonasMyBatisReader(
      sqlSessionFactory, properties.getChunkSize());
  }

  *//** ItemProcessor<Personas, PersonaDomain> Bean *//*
  @Bean(PERSONAS_PROCESSOR)
  public ItemProcessor<Personas, PersonaDomain> processor() {
    return new PersonasToFileProcessor(this.personasMapper);
  }

  *//** FlatFileItemWriter<PersonaDomain> *//*
  @Bean(PERSONAS_ITEM_WRITER)
  public FlatFileItemWriter<PersonaDomain> writePersonasToFile() {

    return new FlatFileItemWriterBuilder<PersonaDomain>()
      .name(PERSONAS_ITEM_WRITER)
      .resource(new FileSystemResource(new File(this.properties.getCvsPath())))
      .lineAggregator(new PassThroughLineAggregator<>())
      .build();
  }

  *//**
   * Feign Step bean
   *
   * @param reader
   * @param writer
   *//*
  @Bean(RECIBOS_STEP)
  public Step recibosStep(
    @Qualifier(RECIBOS_ITEM_READER) AbstractPaginatedDataItemReader<ReciboDetailResource> reader,
    @Qualifier(RECIBOS_ITEM_WRITER) ItemWriter<ReciboDetailResource> writer) {
    return stepBuilderFactory
      .get(STEP_FEIGN_RECIBOS_NAME)
      .<ReciboDetailResource, ReciboDetailResource>chunk(properties.getChunkSize())
      .reader(reader)
      .writer(writer)
      .build();
  }

  *//**
   * Recibos feign reader
   *
   * @param recibosOdlService
   * @return
   *//*
  @Bean(RECIBOS_ITEM_READER)
  public RecibosOdlItemReader recibosOdlItemReader(
    RecibosOdlService recibosOdlService) {
    return new RecibosOdlItemReader(recibosOdlService);
  }

  */
  /**
   * consoles writer
   *
   * @return
   *//*
  @Bean(RECIBOS_ITEM_WRITER)
  public ItemWriter<ReciboDetailResource> consoleWriterItem() {
    return new ItemWriter<ReciboDetailResource>() {
      @Override
      public void write(List<? extends ReciboDetailResource> items) throws Exception {
        items.forEach(item -> log.info("final Object: {}", item));
      }
    };
  }*/


  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  /*@Autowired
  private PresupuestoColItemReader presupuestoColItemReader;

  @Autowired
  private PrespColProcessor prespColProcessor;

  @Autowired
  private PresupuestoColItemWriter presupuestoColItemWriter;
  @Autowired
  private PresupuestoIndvItemReader presupuestoIndvItemReader;

  @Autowired
  private PrespIndvProcessor prespIndvProcessor;

  @Autowired
  private PresupuestoIndItemWriter presupuestoIndItemWriter;

  @Bean
  public Step presupuestoColStep() {
    return stepBuilderFactory.get("presupuestoColStep")
      .<Presupuestos, Presupuestos>chunk(10)
      .reader(presupuestoColItemReader)
      .processor(prespColProcessor)
      .writer(presupuestoColItemWriter)
      .build();
  }

  @Bean
  public Step presupuestoIndStep() {
    return stepBuilderFactory.get("presupuestoStep")
      .<Presupuestos, Presupuestos>chunk(10)
      .reader(presupuestoIndvItemReader)
      .processor(prespIndvProcessor)
      .writer(presupuestoIndItemWriter)
      .build();
  }

  @Bean
  public Job presupuestoJob() {
    return jobBuilderFactory.get("presupuestoJob")
      .start(presupuestoColStep())
      .next(presupuestoIndStep())
      .build();
  }*/

  @Bean
  public Job job(JobBuilderFactory jobBuilderFactory,
                 @Qualifier(StepPresCol.STEP_NAME) Step step1,
                 @Qualifier(StepPresIndv.STEP_NAME) Step step2,
                 @Value("${spring.application.name}") String jobName) {
    return jobBuilderFactory
      .get(jobName)
      .incrementer(new RunIdIncrementer())
      .start(step1)
      .next(step2)
      .build();
  }


}
