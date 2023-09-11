package com.santalucia.cdc.core.service.impl;

import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.client.PresupuestoColectivoApiControllerApiClient;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.EntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.client.HistoricoPresupuestoColectivoApiControllerApiClient;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.client.HistoricoPresupuestoColectivoEntityControllerApiClient;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.PagedModelEntityModelPresupuestoColectivoResource;
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
  private final PresupuestoColectivoApiControllerApiClient presupuestosColectivoApiClient;
  private final HistoricoPresupuestoColectivoApiControllerApiClient histPresupuestoColectivoApiClient;
  private final HistoricoPresupuestoColectivoEntityControllerApiClient historicoPresupuestoColectivoEntityControllerApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoColectivoClientService(PresupuestoColectivoDomainMapper presupuestoColectivoDomainMapper, HistPresupuestoColectivoDomainMapper histPresupuestoColectivoDomainMapper, PresupuestoColectivoApiControllerApiClient presupuestosColectivoApiClient, HistoricoPresupuestoColectivoApiControllerApiClient histPresupuestoColectivoApiClient, HistoricoPresupuestoColectivoEntityControllerApiClient historicoPresupuestoColectivoEntityControllerApiClient, PresupuestosUtilsService presupuestosUtils, AppCustomFeaturesProperties properties) {
    this.presupuestoColectivoDomainMapper = presupuestoColectivoDomainMapper;
    this.histPresupuestoColectivoDomainMapper = histPresupuestoColectivoDomainMapper;
    this.presupuestosColectivoApiClient = presupuestosColectivoApiClient;
    this.histPresupuestoColectivoApiClient = histPresupuestoColectivoApiClient;
    this.historicoPresupuestoColectivoEntityControllerApiClient = historicoPresupuestoColectivoEntityControllerApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para la busqueda de presupuestos colectivos no anonimizados
   * @param indAnonimizado
   * @param indFormalizado
   * @return
   */

  @Override
  public List<PresupuestoColectivoDomain> findCollectiveBudgets(String indAnonimizado, String indFormalizado){

    log.info("Buscando presupuestos colectivos no anonimizados");

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PagedModelEntityModelPresupuestoColectivoResource result = presupuestosColectivoApiClient
      .findAllAdvancedPresupuestosColectivos(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizado, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll((Collection<? extends PresupuestoColectivoDomain>) presupuestoColectivoDomainMapper.toDomain((EntityModelPresupuestoColectivoResource) result.getEmbedded().getPresupuestos()));
      while (pageNum < maxPages && !end) {
        result = presupuestosColectivoApiClient
          .findAllAdvancedPresupuestosColectivos(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(indAnonimizado, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosColectivos.addAll((Collection<? extends PresupuestoColectivoDomain>) presupuestoColectivoDomainMapper.toDomain((EntityModelPresupuestoColectivoResource) result.getEmbedded().getPresupuestos()));
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
