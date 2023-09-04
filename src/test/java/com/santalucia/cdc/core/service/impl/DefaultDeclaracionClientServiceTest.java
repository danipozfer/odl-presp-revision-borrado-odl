package com.santalucia.cdc.core.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.mappers.budget.DeclaracionDomainMapper;
import com.santalucia.cdc.core.mappers.budget.HistDeclaracionDomainMapper;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class DefaultDeclaracionClientServiceTest {

  @Mock
  private DeclaracionDomainMapper declaracionDomainMapper;

  @Mock
  private HistDeclaracionDomainMapper histDeclaracionDomainMapper;

  @Mock
  private PresupuestosDeclaracionesApiClient declaracionApiClient;

  @Mock
  private HistPresupuestosDeclaracionesApiClient histDeclaracionApiClient;

  @Mock
  private PresupuestosUtilsService presupuestosUtils;

  @Mock
  private AppCustomFeaturesProperties properties;

  @InjectMocks
  private DefaultDeclaracionClientService declaracionClientService;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testFindHistoricDeclarationByIdres() {
    // Define your test data and expected results
    String idPresupuestoODL = "123";
    List<DeclaracionDomain> expectedDeclarations = new ArrayList<>(); // Replace with your expected result

    // Mock the dependencies
    when(histDeclaracionApiClient.findAllPresupuestosDeclaracionUsingGET(any(UUID.class), anyMap(), any(PageRequest.class)))
      .thenReturn(/* Mock the response */);
    when(histDeclaracionDomainMapper.toDomainsfromResources(any()))
      .thenReturn(expectedDeclarations);

    // Call the method to test
    List<DeclaracionDomain> result = declaracionClientService.findHistoricDeclarationByIdres(idPresupuestoODL);

    // Verify the results
    assertThat(result).isEqualTo(expectedDeclarations);
  }

  @Test
  public void testFindDeclarationByIdPres() {
    // Define your test data and expected results
    String idPresupuestoODL = "123";
    List<DeclaracionDomain> expectedDeclarations = new ArrayList<>(); // Replace with your expected result

    // Mock the dependencies
    when(declaracionApiClient.findAllPresupuestosDeclaracionUsingGET(any(UUID.class), anyMap(), any(PageRequest.class)))
      .thenReturn(/* Mock the response */);
    when(declaracionDomainMapper.toDomain(any()))
      .thenReturn(expectedDeclarations);

    // Call the method to test
    List<DeclaracionDomain> result = declaracionClientService.findDeclarationByIdPres(idPresupuestoODL);

    // Verify the results
    assertThat(result).isEqualTo(expectedDeclarations);
    // You can add more assertions based on your requirements
  }

}

