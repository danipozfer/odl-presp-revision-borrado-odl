package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.individualBudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.exception.BudgetNotFoundException;
import com.santalucia.cdc.core.mappers.HistPresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.mappers.PresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoIndividiualClientService;
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
   * Metodo para insertar un nuevo presupuesto individual
   *
   * @param presupuestoIndividual
   */
  @Override
  public void insertIndividualBudget(PresupuestoIndividualDomain presupuestoIndividual) {
    PresupuestoIndividualRequestBodyResource input = presupuestoIndividualDomainMapper.toResource(presupuestoIndividual);
    UUID uuid = presupuestosUtils.getOrSetUUID(null);
    insertAsyncIndividualBudget(input, uuid);
  }

  /**
   *
   * @param input
   * @param uuid
   * @return
   */
  @Async
  private CompletableFuture<Void> insertAsyncIndividualBudget(final PresupuestoIndividualRequestBodyResource input,
                                                              final UUID uuid) {
    return CompletableFuture
      .runAsync(() -> presupuestosIndividualApiClient.savePresupuestoIndividualUsingPOST(uuid,
        input, Optional.empty(), Optional.empty()));
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
   * Metodo para obtener un presupuesto individual
   *
   * @param idPresupuestoODL
   */
  @Override
  public PresupuestoIndividualDomain getIndividualBudget(String idPresupuestoODL) {
    PresupuestoIndividualDomain result = null;
    EntityModelPresupuestoIndividualResource presupuestoIndividual = findApiSnapshotIndividualBudget(idPresupuestoODL);
    if (presupuestoIndividual != null) {
      result = presupuestoIndividualDomainMapper.toDomain(presupuestoIndividual);
    }
    return result;
  }

  /**
   * Metodo para obtener la ultima foto de un presupuesto individual
   *
   * @param idPresupuestoODL
   */
  @Override
  public EntityModelPresupuestoIndividualResource findApiSnapshotIndividualBudget(String idPresupuestoODL) {
    log.info("Buscando presupuesto con idPresupuestoODL {}", idPresupuestoODL);
    EntityModelPresupuestoIndividualResource result = null;
    PagedModelEntityModelPresupuestoIndividualResource odlResult = presupuestosIndividualApiClient
      .findAllPresupuestosPresupuestoIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL, "", ""), PageRequest.of(0, 1))
      .getBody();
    if (odlResult != null && !odlResult.getEmbedded().getDeclaracion().isEmpty()) {
      result = odlResult.getEmbedded().getDeclaracion().get(0);
    }
    return result;
  }

  /**
   * Metodo para insertar en historico un presupuesto individual
   * @param individualBudget
   */
  @Override
  public PresupuestoIndividualDomain insertHistoricIndividualBudget(PresupuestoIndividualDomain individualBudget) {
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PresupuestoIndividualRequestBodyResource input = histPresupuestoIndividualDomainMapper
      .toResource(individualBudget);
    return histPresupuestoIndividualDomainMapper.toDomain(histPresupuestoIndividualApiClient
      .savePresupuestoIndividualUsingPOST(presupuestosUtils.getOrSetUUID(null), input, Optional.empty(), Optional.empty()).getBody());
  }

  /**
   * Metodo para buscar un presupuesto individual en la coleccion de historico
   *
   * @param idPresupuestoODL
   * @param versPresupuesto
   */
  @Override
  public List<PresupuestoIndividualDomain> findAllHistoricIndividualBudget(String idPresupuestoODL, String versPresupuesto) {
    log.info("Buscando presupuestos individuales historicos con idPresupuestoODL {} y versPresupuesto {}", idPresupuestoODL,
      versPresupuesto);

    int pageNum = 1;
    List<PresupuestoIndividualDomain> presupuestosIndividuales = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.individual.api.model.PagedModelEntityModelPresupuestoIndividualResource result = histPresupuestoIndividualApiClient
      .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL, versPresupuesto, ""),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(histPresupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoIndividual()));
      while (pageNum < maxPages && !end) {
        result = histPresupuestoIndividualApiClient
          .findAllPresupuestosIndividualesUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL, versPresupuesto, ""),
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
   * Metodo para hacer rollback de un presupuesto individual en el historico
   *
   * @param idPresupuestoODL
   * @param versPresupuestoODL
   * @param uuid
   */
  @Override
  public void rollbackIndividualBudget(String idPresupuestoODL, String versPresupuestoODL, UUID uuid) {
    String individualBudgetId = findApiIdHistoricIndividualBudget(idPresupuestoODL, versPresupuestoODL);
    if (individualBudgetId == null) {
      log.error("Presupuesto individual no encontrado. idPresupuestoODL {}, versPresupuestoODL: {}", idPresupuestoODL, versPresupuestoODL);
      throw new BudgetNotFoundException(idPresupuestoODL);
    }
    histPresupuestoIndividualApiClient.deletePresupuestosIndividualesUsingDELETE(individualBudgetId, uuid, Optional.empty(), Optional.empty());
  }

  /**
   * Metodo para buscar un presupuesto individual en el historico
   *
   * @param idPresupuestoODL
   * @param versPresupuestoODL
   */
  @Override
  public String findApiIdHistoricIndividualBudget(String idPresupuestoODL, String versPresupuestoODL) {
    log.info("Buscando presupuesto individual historico con idPresupuestoODL {} y versPresupuestoODL {}", idPresupuestoODL,
      versPresupuestoODL);
    String resultId = null;
    com.santalucia.arq.ams.odl.historico.presupuestos.individual.api.model.PagedModelEntityModelPresupuestosIndividualesResource result = histPresupuestoIndividualApiClient
      .findAllPresupuestosIndividualesUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL, "", versPresupuestoODL), PageRequest.of(0, 1))
      .getBody();

    if (result != null && !result.getEmbedded().getPresupuestoIndividual().isEmpty()) {
      resultId = result.getEmbedded().getPresupuestoIndividual().get(0).getId();
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
                                                     String versPresupuestoODL, String numIdAgrupacion) {
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
    if (StringUtils.isNotBlank(numIdAgrupacion)) {
      mapParams.put("datosIdentificativos.numIdAgrupacion", List.of(numIdAgrupacion));
    }
    return mapParams;
  }
}
