package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.mappers.budget.HistPresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.service.HistPresupuestoIndividualClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
@Slf4j
@Service
public class DefaultHistPresupuestoIndividualClientService implements HistPresupuestoIndividualClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;
  private final HistPresupuestoIndividualDomainMapper historicoPresupuestoIndividualDomainMapper;
  private final HistoricoPresupuestoIndividualApiClient historicoPresupuestoIndividualApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultHistPresupuestoIndividualClientService(HistPresupuestoIndividualDomainMapper histPresupuestoIndividualDomainMapper,
                                                       HistoricoPresupuestoIndividualApiClient historicoPresupuestoIndividualApiClient,
                                                       PresupuestosUtilsService presupuestosUtils, AppCustomFeaturesProperties properties) {
    this.historicoPresupuestoIndividualDomainMapper = histPresupuestoIndividualDomainMapper;
    this.historicoPresupuestoIndividualApiClient = historicoPresupuestoIndividualApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para buscar un presupuesto individual en la coleccion de historico
   * no anonimizados
   *
   * @param indAnonimizacion
   * @param indFormalizado
   */
  @Override
  public List<PresupuestoIndividualDomain> findAllHistoricIndividualBudget(String indAnonimizacion, String indFormalizado) {
    log.info("Buscando presupuestos individuales historicos no anonimizados");

    int pageNum = 1;
    List<PresupuestoIndividualDomain> presupuestosIndividuales = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.individual.api.model.PagedModelEntityModelPresupuestoIndividualResource result = historicoPresupuestoIndividualApiClient
      .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(historicoPresupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoIndividual()));
      while (pageNum < maxPages && !end) {
        result = historicoPresupuestoIndividualApiClient
          .findAllPresupuestosIndividualesUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(indAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosIndividuales.addAll(historicoPresupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoIndividual()));
        }
      }
    }

    return presupuestosIndividuales;
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
      PresupuestoIndividualRequestBodyResource input = historicoPresupuestoIndividualDomainMapper.toResource(individualBudget);
      result = historicoPresupuestoIndividualDomainMapper
        .toDomain(historicoPresupuestoIndividualApiClient.savePresupuestoIndividualUsingPUT(individualBudgetId,
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
