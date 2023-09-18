package com.santalucia.cdc.core.service.impl;

import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.client.PresupuestoColectivoApiClient;
import com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
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
  private final PresupuestoColectivoApiClient presupuestosColectivoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoColectivoClientService(PresupuestoColectivoDomainMapper presupuestoColectivoDomainMapper,
                                                  PresupuestoColectivoApiClient presupuestosColectivoApiClient,
                                                  PresupuestosUtilsService presupuestosUtils, AppCustomFeaturesProperties properties) {
    this.presupuestoColectivoDomainMapper = presupuestoColectivoDomainMapper;
    this.presupuestosColectivoApiClient = presupuestosColectivoApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para buscar presupuestos colectivos no anonimizados ni formalizados
   *
   * @param indAnonimizacion indica si un presupuesto ha sido anonimizado
   * @param indFormalizado indica si un presupuesto ha pasado a poliza
   * @return
   */
  @Override
  public List<PresupuestoColectivoDomain> findCollectiveBudgets(String indAnonimizacion, String indFormalizado, String numIdAgrupacion){

    log.info("Buscando presupuestos historicos no anonimizados ni convertidos en polizas");

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PagedModelEntityModelPresupuestoColectivoResource result = presupuestosColectivoApiClient
      .findAllAdvancedPresupuestosColectivos(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado,numIdAgrupacion),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll(presupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded()));
      while (pageNum < maxPages && !end) {
        result = presupuestosColectivoApiClient
          .findAllAdvancedPresupuestosColectivos(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(indAnonimizacion, indFormalizado,numIdAgrupacion),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosColectivos.addAll(presupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded()));
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
        .toDomain(presupuestosColectivoApiClient.updatePresupuestoColectivo(
          presupuestosUtils.getOrSetUUID(uuid),collectiveBudgetId, input).getBody());
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
                                                     String indFormalizado, String numIdAgrupacion) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);

    if (StringUtils.isNotBlank(indAnonimizacion)){
      mapParams.put("datosIdentificativos.indAnonimizacion", List.of(indAnonimizacion));
    }
    if (StringUtils.isNotBlank(indFormalizado)){
      mapParams.put("datosIdentificativos.indFormalizado", List.of(indFormalizado));
    }
    if (StringUtils.isNotBlank(numIdAgrupacion)){
      mapParams.put("datosIdentificativos.indFormalizado", List.of(indFormalizado));
    }
    return mapParams;
  }
}
