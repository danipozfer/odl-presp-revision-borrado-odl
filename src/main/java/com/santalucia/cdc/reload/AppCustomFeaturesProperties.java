package com.santalucia.cdc.reload;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 *
 * @author accenture_arq
 *
 */
@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.custom.features")
public class AppCustomFeaturesProperties {

  /**
   * Definicion de chunkSize
   */
  private int retryMaxAttempt=3;
  private long retryInterval=3000L;
  private int attempt= 1;
  private int findallPageSize;
  private String firstVersion;

}
