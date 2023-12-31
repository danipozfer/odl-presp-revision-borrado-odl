package com.santalucia.cdc.core.service.impl;

import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.client.HistoricoPresupuestoColectivoApiClient;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.PagedModelEntityModelPresupuestoColectivoResource;
import com.santalucia.arq.ams.odl.presupuestos.historico.colectivo.api.model.PresupuestoColectivoRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.mappers.budget.HistPresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.service.HistPresupuestoColectivoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@SuppressWarnings("NullAway")
public class DefaultHistPresupuestoColectivoClientService implements HistPresupuestoColectivoClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  private final HistPresupuestoColectivoDomainMapper historicoPresupuestoColectivoDomainMapper;
  private final HistoricoPresupuestoColectivoApiClient historicoPresupuestosColectivoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultHistPresupuestoColectivoClientService(HistPresupuestoColectivoDomainMapper histPresupuestoColectivoDomainMapper,
                                                      HistoricoPresupuestoColectivoApiClient historicoPresupuestosColectivoApiClient,
                                                      PresupuestosUtilsService presupuestosUtils, AppCustomFeaturesProperties properties) {
    this.historicoPresupuestoColectivoDomainMapper = histPresupuestoColectivoDomainMapper;
    this.historicoPresupuestosColectivoApiClient = historicoPresupuestosColectivoApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para buscar presupuestos colectivos en la coleccion de historico
   * no anonimizados
   *
   * @param indAnonimizacion
   * @param indFormalizado
   */
  @Override
  public List<PresupuestoColectivoDomain> findAllHistoricCollectiveBudget(String indAnonimizacion, String indFormalizado) {
    log.info("Buscando presupuestos colectivos historicos no anonimizados");

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    PagedModelEntityModelPresupuestoColectivoResource result = historicoPresupuestosColectivoApiClient
      .findAllAdvancedHistoricoPresupuestosColectivos(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if (result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll(historicoPresupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestosColectivos()));
      while (pageNum < maxPages && !end) {
        result = historicoPresupuestosColectivoApiClient
          .findAllAdvancedHistoricoPresupuestosColectivos(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(indAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        } else {
          pageNum++;
          presupuestosColectivos.addAll(historicoPresupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestosColectivos()));
        }
      }
    }

    return presupuestosColectivos;
  }

  /**
   * Metodo para actualizar un presupuesto colectivo historico
   *
   * @param collectiveBudget
   * @param collectiveBudgetId
   * @param
   */
  @Override
  public PresupuestoColectivoDomain updateHistCollectiveBudget(PresupuestoColectivoDomain collectiveBudget, String collectiveBudgetId) {
    PresupuestoColectivoDomain result = null;
    if (collectiveBudgetId != null) {
      PresupuestoColectivoRequestBodyResource input = historicoPresupuestoColectivoDomainMapper.toResource(collectiveBudget);
      result = historicoPresupuestoColectivoDomainMapper
        .toDomain(historicoPresupuestosColectivoApiClient.updateHistoricoPresupuestoColectivo(presupuestosUtils.getOrSetUUID(null),
          collectiveBudgetId, input).getBody());
    }
    return result;
  }

  /**
   * Metodo para obtener parametros para la consulta
   *
   * @param indAnonimizacion
   * @param indFormalizado
   * @return
   */
  private Map<String, List<String>> getMapParamQuery(String indAnonimizacion,
                                                     String indFormalizado) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);

    if (StringUtils.isNotBlank(indAnonimizacion)) {
      mapParams.put("datosIdentificativos.indAnonimizacion", List.of(indAnonimizacion));
    }
    if (StringUtils.isNotBlank(indFormalizado)) {
      mapParams.put("datosIdentificativos.indFormalizado", List.of(indFormalizado));
    }
    return mapParams;
  }
}
