package com.santalucia.cdc.core.service.impl;

import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.mappers.budget.HistPresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.mappers.budget.PresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoColectivoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DefaultPresupuestoColectivoClientService implements PresupuestoColectivoClientService {
  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired
  private final PresupuestoColectivoDomainMapper presupuestoColectivoDomainMapper;
  private final HistPresupuestoColectivoDomainMapper histPresupuestoColectivoDomainMapper;
  private final PresupuestoColectivoApiClient presupuestosColectivoApiClient;
  private final HistoricoPresupuestoColectivoApiApiClient histPresupuestoColectivoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoColectivoClientService(PresupuestoColectivoDomainMapper presupuestoColectivoDomainMapper, HistPresupuestoColectivoDomainMapper histPresupuestoColectivoDomainMapper, PresupuestoColectivoApiClient presupuestosColectivoApiClient, HistoricoPresupuestoColectivoApiApiClient histPresupuestoColectivoApiClient, PresupuestosUtilsService presupuestosUtils, AppCustomFeaturesProperties properties) {
    this.presupuestoColectivoDomainMapper = presupuestoColectivoDomainMapper;
    this.histPresupuestoColectivoDomainMapper = histPresupuestoColectivoDomainMapper;
    this.presupuestosColectivoApiClient = presupuestosColectivoApiClient;
    this.histPresupuestoColectivoApiClient = histPresupuestoColectivoApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para obtener un presupuesto colectivo
   *
   * @param indAnonimizacion
   * @param indFormalizado
   */
  @Override
  public PresupuestoColectivoDomain getCollectiveBudget(String indAnonimizacion, String indFormalizado) {
    PresupuestoColectivoDomain result = null;
    PresupuestoColectivoResource presupuestoColectivo = findApiSnapshotCollectiveBudget(indAnonimizacion, indFormalizado);
    if (presupuestoColectivo != null) {
      result = presupuestoColectivoDomainMapper.toDomain(presupuestoColectivo);
    }
    return result;
  }

  /**
   * Metodo para obtener la ultima foto de un presupuesto colectivo
   *
   * @param indAnonimizacion
   * @param indFormalizado
   */
  @Override
  public PresupuestoColectivoResource findApiSnapshotCollectiveBudget(String indAnonimizacion, String indFormalizado) {
    log.info("Buscando presupuesto no anonimizado");
    PresupuestoColectivoResource result = null;
    PagedModelEntityModelPresupuestoColectivoResource odlResult = presupuestosColectivoApiClient
      .findAllPresupuestosPresupuestoColectivoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado), PageRequest.of(0, 1))
      .getBody();
    if (odlResult != null && !odlResult.getEmbedded().getDeclaracion().isEmpty()) {
      result = odlResult.getEmbedded().getDeclaracion().get(0);
    }
    return result;
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
      PresupuestoColectivoRequestBodyResource input = presupuestoColectivoDomainMapper.toResource(collectiveBudget);
      result = presupuestoColectivoDomainMapper
        .toDomain(presupuestosColectivoApiClient.savePresupuestosColectivosUsingPUT(collectiveBudgetId,
          presupuestosUtils.getOrSetUUID(uuid), input, Optional.empty(), Optional.empty()).getBody());
    }
    return result;
  }

  /**
   * Metodo para obtener parametros para la consulta
   *
   * @param indAnonimizacion
   * @param indFormalizado
   *
   * @return
   */
  private Map<String, List<String>> getMapParamQuery(String indAnonimizacion,
                                                     String indFormalizado) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);

    if (StringUtils.isNotBlank(indAnonimizacion)){
      mapParams.put("datosIdentificativos.indAnonimizacion", List.of(indAnonimizacion));
    }
    if (StringUtils.isNotBlank(indFormalizado)){
      mapParams.put("datosIdentificativos.indFormalizado", List.of(indFormalizado));
    }
    return mapParams;
  }
}
