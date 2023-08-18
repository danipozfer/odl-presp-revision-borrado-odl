package com.santalucia.cdc;



import com.fasterxml.jackson.databind.ObjectMapper;

import feign.FeignException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.santalucia.arq.ams.odl.recibos.api.client.RecibosApiClient;
import com.santalucia.arq.ams.odl.recibos.api.model.CollectionModelRecibosResource;




@Slf4j
@SpringBootTest
@SpringBatchTest
class DummyJobTest {

  @MockBean
  private RecibosApiClient recibosApiClient;
  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  @DisplayName("El job se ejecuta correctamente y finaliza con estado COMPLETED")
  @Sql(scripts = {"/sql/schemas/personas-schema.sql", "/sql/data/personas-data.sql"})
  void assert_that_job_ends_with_status_completed() throws Exception {
    ResponseEntity<CollectionModelRecibosResource> responseEntity =
      ResponseEntity.ok().body(RecibosTestFactory.getCollectionRecibos(mapper));

    ResponseEntity<CollectionModelRecibosResource> responseEntityEmpty =
      ResponseEntity.ok().body(RecibosTestFactory.getCollectionRecibosEmptyFactory());

    when(recibosApiClient.findAllFilterRecibos(
      Mockito.any(), Mockito.anyMap(), Mockito.any()))
      .thenReturn(responseEntity)
      .thenReturn(responseEntityEmpty);

    log.info("Test job");

    assertThat(jobLauncherTestUtils.launchJob().getExitStatus())
      .isEqualTo(ExitStatus.COMPLETED);
  }



  @Test
  @DisplayName("Test que lanza el job y evalua que realiza los retrys con un resultado COMPLETED")
  @Sql(scripts = {"/sql/schemas/personas-schema.sql", "/sql/data/personas-data.sql"})
  void assert_that_job_retrys_ends_with_status_completed() throws Exception {
    ResponseEntity<CollectionModelRecibosResource> responseEntity =
      ResponseEntity.ok().body(RecibosTestFactory.getCollectionRecibos(mapper));

    ResponseEntity<CollectionModelRecibosResource> responseEntityEmpty =
      ResponseEntity.ok().body(RecibosTestFactory.getCollectionRecibosEmptyFactory());

    FeignException.GatewayTimeout gatewayTimeout = Mockito.mock(FeignException.GatewayTimeout.class);
    Mockito.when(gatewayTimeout.status()).thenReturn(504);

    when(recibosApiClient.findAllFilterRecibos(
      Mockito.any(), Mockito.anyMap(), Mockito.any()))
      .thenThrow(gatewayTimeout).thenThrow(gatewayTimeout).thenThrow(gatewayTimeout)
      .thenThrow(gatewayTimeout).thenThrow(gatewayTimeout).thenThrow(gatewayTimeout)
      .thenReturn(responseEntity)
      .thenReturn(responseEntityEmpty);

    log.info("Test job");


    assertThat(jobLauncherTestUtils.launchJob().getExitStatus())
      .isEqualTo(ExitStatus.COMPLETED);
  }

}
