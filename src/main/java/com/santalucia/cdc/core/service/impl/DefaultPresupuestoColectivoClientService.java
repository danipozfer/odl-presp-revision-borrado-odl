package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.exception.BudgetNotFoundException;
import com.santalucia.cdc.core.mappers.budget.HistPresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.mappers.budget.PresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoColectivoClientService;
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
public class DefaultPresupuestoColectivoClientService implements PresupuestoColectivoClientService {
  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired
  private final PresupuestoColectivoDomainMapper presupuestoColectivoDomainMapper;
  private final HistPresupuestoColectivoDomainMapper histPresupuestoColectivoDomainMapper;
  private final PresupuestosColectivosApiClient presupuestosColectivoApiClient;
  private final HistoricoPresupuestosColectivosApiClient histPresupuestoColectivoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoColectivoClientService(PresupuestoColectivoDomainMapper pcMapper,
                                                  HistPresupuestoColectivoDomainMapper hMapper,
                                                  PresupuestosColectivosApiClient pcClient,
                                                  HistoricoPresupuestosColectivosApiClient hClient,
                                                  PresupuestosUtilsService pUtils,
                                                  AppCustomFeaturesProperties prop) {
    this.presupuestoColectivoDomainMapper = pcMapper;
    this.histPresupuestoColectivoDomainMapper = hMapper;
    this.presupuestosColectivoApiClient = pcClient;
    this.histPresupuestoColectivoApiClient = hClient;
    this.presupuestosUtils = pUtils;
    this.properties = prop;
  }

  /**
   * Metodo para la busqueda de presupuestos colectivos no anonimizados
   * @param fechaAnonimizacion
   * @param indFormalizado
   * @return
   */

  @Override
  public List<PresupuestoColectivoDomain> findCollectiveBudgets(Instant fechaAnonimizacion, String indFormalizado){

    log.info("Buscando presupuestos colectivos no anonimizados");

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PagedModelEntityModelPresupuestoColectivoResource result = presupuestosColectivoApiClient
      .findAllPresupuestosColectivoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(fechaAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll(presupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
      while (pageNum < maxPages && !end) {
        result = presupuestosColectivoApiClient
          .findAllPresupuestosColectivoUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(fechaAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosColectivos.addAll(presupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
        }
      }
    }

    return presupuestosColectivos;
  }

  /**
   * Metodo para buscar presupuestos colectivos en la coleccion de historico
   * no anonimizados
   *
   * @param fechaAnonimizacion
   * @param indFormalizado
   */
  @Override
  public List<PresupuestoColectivoDomain> findAllHistoricCollectiveBudget(Instant fechaAnonimizacion, String indFormalizado) {
    log.info("Buscando presupuestos colectivos historicos no anonimizados");

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelPresupuestoColectivoResource result = histPresupuestoColectivoApiClient
      .findAllPresupuestosColectivosUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(fechaAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll(histPresupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
      while (pageNum < maxPages && !end) {
        result = histPresupuestoColectivoApiClient
          .findAllPresupuestosColectivosUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(fechaAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosColectivos.addAll(histPresupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
        }
      }
    }

    return presupuestosColectivos;
  }

  /**
   * Metodo para actualizar un presupuesto colectivo
   *
   * @param collectiveBudget
   * @param collectiveBudgetId
   * @param uuid
   */
  @Override
  public PresupuestoColectivoDomain updateCollectiveBudget(PresupuestoColectivoDomain collectiveBudget, String collectiveBudgetId, UUID uuid) {
    PresupuestoColectivoDomain result = null;
    if (collectiveBudgetId != null) {
      PresupuestosColectivosRequestBodyResource input = presupuestoColectivoDomainMapper.toResource(collectiveBudget);
      result = presupuestoColectivoDomainMapper
        .toDomain(presupuestosColectivoApiClient.savePresupuestosColectivosUsingPUT(collectiveBudgetId,
          presupuestosUtils.getOrSetUUID(uuid), input, Optional.empty(), Optional.empty()).getBody());
    }
    return result;
  }

  /**
   * Metodo para actualizar un presupuesto colectivo historico
   *
   * @param collectiveBudget
   * @param collectiveBudgetId
   * @param uuid
   */
  @Override
  public PresupuestoColectivoDomain updateHistCollectiveBudget(PresupuestoColectivoDomain collectiveBudget, String collectiveBudgetId, UUID uuid) {
    PresupuestoColectivoDomain result = null;
    if (collectiveBudgetId != null) {
      PresupuestosColectivosRequestBodyResource input = histPresupuestoColectivoDomainMapper.toResource(collectiveBudget);
      result = histPresupuestoColectivoDomainMapper
        .toDomain(histPresupuestoColectivoApiClient.savePresupuestosColectivosUsingPUT(collectiveBudgetId,
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
