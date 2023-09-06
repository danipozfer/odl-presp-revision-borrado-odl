package com.santalucia.cdc.core.service.impl;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.mappers.budget.HistObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.mappers.budget.ObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class DefaultObjetoAseguradoClientServiceTest {

  @Mock
  private ObjetoAseguradoApiClient objetoAseguradoApiClient;

  @Mock
  private HistObjetoAseguradoApiClient histObjetoAseguradoApiClient;

  @Mock
  private PresupuestosUtilsService presupuestosUtils;

  @Mock
  private AppCustomFeaturesProperties properties;

  private DefaultObjetoAseguradoClientService objetoAseguradoClientService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    objetoAseguradoClientService = new DefaultObjetoAseguradoClientService(
      mock(ObjetoAseguradoDomainMapper.class),
      mock(HistObjetoAseguradoDomainMapper.class),
      objetoAseguradoApiClient,
      histObjetoAseguradoApiClient,
      presupuestosUtils,
      properties
    );
  }

  @Test
  public void testFindAllHistoricSecuredObject() {
    // Simula el comportamiento del cliente de la API
    when(histObjetoAseguradoApiClient.findAllPresupuestosObjetoAseguradoUsingGET(
      any(), any(), any(PageRequest.class)))
      .thenReturn(mock(com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelObjetoAseguradoResource.class));

    // Llama al método que se va a probar
    List<ObjetosAseguradosDomain> result = objetoAseguradoClientService.findAllHistoricSecuredObject("idPresupuestoODL");

    // Realiza afirmaciones con AssertJ
    assertThat(result).isNotNull();
    assertThat(result).isEmpty(); // Ajusta esta afirmación según lo que esperes
  }

  @Test
  public void testFindObjetoAseguradoByIdPres() {
    // Simula el comportamiento del cliente de la API
    when(objetoAseguradoApiClient.findAllPresupuestosObjetoAseguradoGET(
      any(), any(), any(PageRequest.class)))
      .thenReturn(mock(com.santalucia.arq.ams.odl.presupuestos.declaracion.api.model.PagedModelEntityModelDeclaracionResource.class));

    // Llama al método que se va a probar
    List<DeclaracionDomain> result = objetoAseguradoClientService.findObjetoAseguradoByIdPres("idPresupuestoODL");

    // Realiza afirmaciones con AssertJ
    assertThat(result).isNotNull();
    assertThat(result).isEmpty(); // Ajusta esta afirmación según lo que esperes
  }

  // Agrega más pruebas según sea necesario para cubrir otros métodos

}

