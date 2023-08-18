package com.santalucia.cdc;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.retry.annotation.EnableRetry;

import lombok.Generated;

import com.santalucia.arq.ams.componentes.retry.properties.reload.RetryConfigProperties;


/**
 * Spring Initializer created an application class
 *
 */
@SpringBootApplication
@EnableRetry
@EnableConfigurationProperties(RetryConfigProperties.class)
public class Application {

  /**
   * method to launch the application
   *
   * @param args
   */
  @Generated
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
