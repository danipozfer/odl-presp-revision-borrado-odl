package com.santalucia.cdc;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

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


@SpringBootTest
@SpringBatchTest
class DummyStepTest {

  @MockBean
  private RecibosApiClient recibosApiClient;
  @Autowired
  private ObjectMapper mapper;

  @Autowired
  private JobLauncherTestUtils jobLauncherTestUtils;

  @Test
  @DisplayName("El test se ejecuta correctamente y finaliza con estado COMPLETED")
  @Sql(scripts = {"/sql/schemas/personas-schema.sql", "/sql/data/personas-data.sql"})
  void assert_that_step_ends_with_status_completed() throws Exception {
    assertThat(jobLauncherTestUtils.launchStep("personas-to-file").getExitStatus()).isEqualTo(ExitStatus.COMPLETED);
  }

  @Test
  @DisplayName("El test se ejecuta step recibos correctamente y finaliza con estado COMPLETED")
  void assert_that_step_feign_ends_with_status_completed() throws Exception {
    ResponseEntity<CollectionModelRecibosResource> responseEntity =
      ResponseEntity.ok().body(RecibosTestFactory.getCollectionRecibos(mapper));

    ResponseEntity<CollectionModelRecibosResource> responseEntityEmpty =
      ResponseEntity.ok().body(RecibosTestFactory.getCollectionRecibosEmptyFactory());

    when(recibosApiClient.findAllFilterRecibos(
      Mockito.any(), Mockito.anyMap(), Mockito.any()))
      .thenReturn(responseEntity)
      .thenReturn(responseEntityEmpty);


    assertThat(
      jobLauncherTestUtils
        .launchStep("recibos-to-console")
        .getExitStatus())
      .isEqualTo(ExitStatus.COMPLETED);
  }

}
