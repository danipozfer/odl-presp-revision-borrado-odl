package com.santalucia.cdc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.JobRepositoryTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import lombok.extern.slf4j.Slf4j;

/*@Slf4j
@SpringBootTest
@SpringBatchTest
class ApplicationTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Autowired
	private JobRepositoryTestUtils jobRepositoryTestUtils;

	@Test
	@DisplayName("Prueba de carga de contexto")
	void contextLoads(ApplicationContext context) {
		assertThat(context).isNotNull();
	    assertThat(jobLauncherTestUtils).isNotNull();
	    assertThat(jobRepositoryTestUtils).isNotNull();
	    log.info("Context Loads!!!");
	}

}*/
