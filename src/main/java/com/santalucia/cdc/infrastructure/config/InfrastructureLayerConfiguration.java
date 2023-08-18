package com.santalucia.cdc.infrastructure.config;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration(proxyBeanMethods = false)
public class InfrastructureLayerConfiguration {

  /**
   * Constructor de clase
   */
  public InfrastructureLayerConfiguration() {
    log.debug("InfrastructureLayerConfiguration loaded");
  }

}
