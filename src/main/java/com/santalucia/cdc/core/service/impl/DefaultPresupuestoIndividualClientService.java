package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.individualBudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.mappers.budget.HistPresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.mappers.budget.PresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoIndividiualClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Slf4j
@Service
public class DefaultPresupuestoIndividualClientService implements PresupuestoIndividiualClientService {
  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired
  private final PresupuestoIndividualDomainMapper presupuestoIndividualDomainMapper;
  private final HistPresupuestoIndividualDomainMapper histPresupuestoIndividualDomainMapper;
  private final PresupuestosIndividualApiClient presupuestosIndividualApiClient;
  private final HistoricoPresupuestosIndividualApiClient histPresupuestoIndividualApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoIndividualClientService(PresupuestoIndividualDomainMapper piMapper,
                                                  HistPresupuestoIndividualDomainMapper hMapper,
                                                  PresupuestosIndividualApiClient piClient,
                                                  HistoricoPresupuestosIndividualApiClient hClient,
                                                  PresupuestosUtilsService pUtils,
                                                  AppCustomFeaturesProperties prop) {
    this.presupuestoIndividualDomainMapper = piMapper;
    this.histPresupuestoIndividualDomainMapper = hMapper;
    this.presupuestosIndividualApiClient = piClient;
    this.histPresupuestoIndividualApiClient = hClient;
    this.presupuestosUtils = pUtils;
    this.properties = prop;
  }


  /**
   * Metodo para la busqueda de presupuestos individuales no anonimizados
   * @param fechaAnonimizacion
   * @param indFormalizado
   * @return
   */

  @Override
  public List<PresupuestoIndividualDomain> findIndividualBudgets(Instant fechaAnonimizacion, String indFormalizado){

    log.info("Buscando presupuestos individuales no anonimizados");

    int pageNum = 1;
    List<PresupuestoIndividualDomain> presupuestosIndividuales = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PagedModelEntityModelPresupuestoColectivoResource result = presupuestosIndividualApiClient
      .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(fechaAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
      while (pageNum < maxPages && !end) {
        result = presupuestosIndividualApiClient
          .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(fechaAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
        }
      }
    }

    return presupuestosIndividuales;
  }

  /**
   * Metodo para buscar un presupuesto individual en la coleccion de historico
   * no anonimizados
   *
   * @param fechaAnonimizacion
   * @param indFormalizado
   */
  @Override
  public List<PresupuestoIndividualDomain> findAllHistoricIndividualBudget(Instant fechaAnonimizacion, String indFormalizado) {
    log.info("Buscando presupuestos individuales historicos no anonimizados");

    int pageNum = 1;
    List<PresupuestoIndividualDomain> presupuestosIndividuales = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.individual.api.model.PagedModelEntityModelPresupuestoIndividualResource result = histPresupuestoIndividualApiClient
      .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(fechaAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(histPresupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoIndividual()));
      while (pageNum < maxPages && !end) {
        result = histPresupuestoIndividualApiClient
          .findAllPresupuestosIndividualesUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(fechaAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosIndividuales.addAll(histPresupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoIndividual()));
        }
      }
    }

    return presupuestosIndividuales;
  }

  /**
   * Metodo para actualizar un presupuesto individual
   *
   * @param individualBudget
   * @param individualBudgetId
   * @param uuid
   */
  @Override
  public PresupuestoIndividualDomain updateIndividualBudget(PresupuestoIndividualDomain individualBudget, String individualBudgetId, UUID uuid) {
    PresupuestoIndividualDomain result = null;
    if (individualBudgetId != null) {
      PresupuestoIndividualRequestBodyResource input = presupuestoIndividualDomainMapper.toResource(individualBudget);
      result = presupuestoIndividualDomainMapper
        .toDomain(presupuestosIndividualApiClient.savePresupuestoIndividualUsingPUT(individualBudgetId,
          presupuestosUtils.getOrSetUUID(uuid), input, Optional.empty(), Optional.empty()).getBody());
    }
    return result;
  }

  /**
   * Metodo para actualizar un presupuesto individual en histórico
   *
   * @param individualBudget
   * @param individualBudgetId
   * @param uuid
   */
  @Override
  public PresupuestoIndividualDomain updateHistIndividualBudget(PresupuestoIndividualDomain individualBudget, String individualBudgetId, UUID uuid) {
    PresupuestoIndividualDomain result = null;
    if (individualBudgetId != null) {
      PresupuestoIndividualRequestBodyResource input = histPresupuestoIndividualDomainMapper.toResource(individualBudget);
      result = histPresupuestoIndividualDomainMapper
        .toDomain(histPresupuestoIndividualApiClient.savePresupuestoIndividualUsingPUT(individualBudgetId,
          presupuestosUtils.getOrSetUUID(uuid), input, Optional.empty(), Optional.empty()).getBody());
    }
    return result;
  }

  /**
   * Metodo para obtener parametros para la consulta
   *
   * @param fechaAnonimizacion
   * @param indFormalizado
   *
   * @return
   */
  private Map<String, List<String>> getMapParamQuery(Instant fechaAnonimizacion,
                                                     String indFormalizado) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    Map<String, List<Instant>> mapDates = new HashMap<>(DEFAULT_INITIAL_CAPACITY);

    if (fechaAnonimizacion == null){
      mapDates.put("fechaYEstado.fecha.fechaAnonimizacion", null);
    }
    if (StringUtils.isNotBlank(indFormalizado)){
      mapParams.put("datosIdentificativos.indFormalizado", List.of(indFormalizado));
    }
    return mapParams;
  }
}
