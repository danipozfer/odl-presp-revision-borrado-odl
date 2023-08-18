package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.exception.BudgetNotFoundException;
import com.santalucia.cdc.core.mappers.HistPresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.mappers.PresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.properties.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class DefaultPresupuestoColectivoClientService {
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
   * Metodo para insertar un nuevo presupuesto colectivo
   *
   * @param presupuestoColectivo
   */
  @Override
  public void insertCollectiveBudget(PresupuestoColectivoDomain presupuestoColectivo) {
    PresupuestosColectivosRequestBodyResource input = presupuestoColectivoDomainMapper.toResource(presupuestoColectivo);
    UUID uuid = presupuestosUtils.getOrSetUUID(null);
    insertAsyncCollectiveBudget(input, uuid);
  }

  /**
   *
   * @param input
   * @param uuid
   * @return
   */
  @Async
  private CompletableFuture<Void> insertAsyncCollectiveBudget(final PresupuestosColectivosRequestBodyResource input,
                                                           final UUID uuid) {
    return CompletableFuture
      .runAsync(() -> presupuestosColectivoApiClient.savePresupuestosColectivosUsingPOST(uuid,
        input, Optional.empty(), Optional.empty()));
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
   * Metodo para obtener un presupuesto colectivo
   *
   * @param idPresupuestoODL
   */
  @Override
  public PresupuestoColectivoDomain getCollectiveBudget(String idPresupuestoODL) {
    PresupuestoColectivoDomain result = null;
    EntityModelPresupuestoColectivoResource presupuestoColectivo = findApiSnapshotCollectiveBudget(idPresupuestoODL);
    if (presupuestoColectivo != null) {
      result = presupuestoColectivoDomainMapper.toDomain(presupuestoColectivo);
    }
    return result;
  }

  /**
   * Metodo para obtener la ultima foto de un presupuesto colectivo
   *
   * @param idPresupuestoODL
   */
  @Override
  public EntityModelPresupuestoColectivoResource findApiSnapshotCollectiveBudget(String idPresupuestoODL) {
    log.info("Buscando presupuesto con idPresupuestoODL {}", idPresupuestoODL);
    EntityModelPresupuestoColectivoResource result = null;
    PagedModelEntityModelPresupuestoColectivoResource odlResult = presupuestosColectivoApiClient
      .findAllPresupuestosPresupuestoColectivoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL, "", ""), PageRequest.of(0, 1))
      .getBody();
    if (odlResult != null && !odlResult.getEmbedded().getDeclaracion().isEmpty()) {
      result = odlResult.getEmbedded().getDeclaracion().get(0);
    }
    return result;
  }

  /**
   * Metodo para insertar en historico un presupuesto colectivo
   * @param collectiveBudget
   */
  @Override
  public PresupuestoColectivoDomain insertHistoricCollectiveBudget(PresupuestoColectivoDomain collectiveBudget) {
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PresupuestoColectivoRequestBodyResource input = histPresupuestoColectivoDomainMapper
      .toResource(collectiveBudget);
    return histPresupuestoColectivoDomainMapper.toDomain(histPresupuestoColectivoApiClient
      .savePresupuestosColectivosUsingPOST(presupuestosUtils.getOrSetUUID(null), input, Optional.empty(), Optional.empty()).getBody());
  }

  /**
   * Metodo para buscar un presupuesto colectivo en la coleccion de historico
   *
   * @param idPresupuestoODL
   * @param versPresupuesto
   */
  @Override
  public List<PresupuestoColectivoDomain> findAllHistoricCollectiveBudget(String idPresupuestoODL, String versPresupuesto) {
    log.info("Buscando presupuestos colectivos historicos con idPresupuestoODL {} y versPresupuesto {}", idPresupuestoODL,
      versPresupuesto);

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelPresupuestoColectivoResource result = histPresupuestoColectivoApiClient
      .findAllPresupuestosColectivosUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL, versPresupuesto, ""),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll(histPresupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
      while (pageNum < maxPages && !end) {
        result = histPresupuestoColectivoApiClient
          .findAllPresupuestosColectivosUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL, versPresupuesto, ""),
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
   * Metodo para hacer rollback de un presupuesto colectivo en el historico
   *
   * @param idPresupuestoODL
   * @param versPresupuestoODL
   * @param uuid
   */
  @Override
  public void rollbackCollectiveBudget(String idPresupuestoODL, String versPresupuestoODL, UUID uuid) {
    String collectiveBudgetId = findApiIdHistoricCollectiveBudget(idPresupuestoODL, versPresupuestoODL);
    if (collectiveBudgetId == null) {
      log.error("Presupuesto colectivo no encontrado. idPresupuestoODL {}, versPresupuestoODL: {}", idPresupuestoODL, versPresupuestoODL);
      throw new BudgetNotFoundException(idPresupuestoODL);
    }
    histPresupuestoColectivoApiClient.deletePresupuestosColectivosUsingDELETE(collectiveBudgetId, uuid, Optional.empty(), Optional.empty());
  }

  /**
   * Metodo para buscar un presupuesto colectivo en el historico
   *
   * @param idPresupuestoODL
   * @param versPresupuestoODL
   */
  @Override
  public String findApiIdHistoricCollectiveBudget(String idPresupuestoODL, String versPresupuestoODL) {
    log.info("Buscando presupuesto colectivo historico con idPresupuestoODL {} y versPresupuestoODL {}", idPresupuestoODL,
      versPresupuestoODL);
    String resultId = null;
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelPresupuestosColectivosResource result = histPresupuestoColectivoApiClient
      .findAllPresupuestosColectivosUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL, "", versPresupuestoODL), PageRequest.of(0, 1))
      .getBody();

    if (result != null && !result.getEmbedded().getPresupuestoColectivo().isEmpty()) {
      resultId = result.getEmbedded().getPresupuestoColectivo().get(0).getId();
    }

    return resultId;
  }

  /**
   * Metodo para obtener parametros para la consulta
   *
   * @param idPresupuestoODL
   * @param versPresupuesto
   * @param versPresupuestoODL
   *
   * @return
   */
  private Map<String, List<String>> getMapParamQuery(String idPresupuestoODL, String versPresupuesto,
                                                     String versPresupuestoODL) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    if (StringUtils.isNotBlank(idPresupuestoODL)) {
      mapParams.put("datosIdentificativos.idPresupuestoODL", List.of(idPresupuestoODL));
    }
    if (StringUtils.isNotBlank(versPresupuesto)) {
      mapParams.put("datosIdentificativos.versPresupuesto", List.of(versPresupuesto));
    }
    if (StringUtils.isNotBlank(versPresupuestoODL)) {
      mapParams.put("datosIdentificativos.versPresupuestoODL", List.of(versPresupuestoODL));
    }
    return mapParams;
  }
}
