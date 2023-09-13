package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.mappers.budget.PresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoIndividiualClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DefaultPresupuestoIndividualClientService implements PresupuestoIndividiualClientService {
  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired
  private final PresupuestoIndividualDomainMapper presupuestoIndividualDomainMapper;
  private final PresupuestosIndividualApiClient presupuestosIndividualApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoIndividualClientService(PresupuestoIndividualDomainMapper presupuestoIndividualDomainMapper,
                                                   PresupuestosIndividualApiClient presupuestosIndividualApiClient,
                                                   PresupuestosUtilsService presupuestosUtils, AppCustomFeaturesProperties properties) {
    this.presupuestoIndividualDomainMapper = presupuestoIndividualDomainMapper;
    this.presupuestosIndividualApiClient = presupuestosIndividualApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para buscar presupuestos individuales no anonimizados ni formalizados
   *
   * @param indAnonimizacion indica si un presupuesto ha sido anonimizado
   * @param indFormalizado indica si un presupuesto ha pasado a poliza
   * @return
   */
  @Override
  public List<PresupuestoIndividualDomain> findIndividualBudgets(String indAnonimizacion, String indFormalizado){

    log.info("Buscando presupuestos individuales no anonimizados ni convertidos en polizas");

    int pageNum = 1;
    List<PresupuestoIndividualDomain> presupuestosIndividuales = new ArrayList<>(DEFAULT_CAPACITY);
    PagedModelEntityModelPresupuestoIndividualResource result = presupuestosIndividualApiClient
      .findAllPresupuestoIndividual(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestos()));
      while (pageNum < maxPages && !end) {
        result = presupuestosIndividualApiClient
          .findAllPresupuestoIndividual(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(indAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestos()));
        }
      }
    }

    return presupuestosIndividuales;
  }
  /**
   * Metodo para obtener un presupuesto individual
   *
   * @param indAnonimizacion
   * @param indFormalizado
  @Override
  public PresupuestoIndividualDomain getIndividualBudget(String indAnonimizacion, String indFormalizado) {
    PresupuestoIndividualDomain result = null;
    EntityModelPresupuestoIndividualResource presupuestoIndividual = findApiSnapshotIndividualBudget(idPresupuestoODL);
    if (presupuestoIndividual != null) {
      result = presupuestoIndividualDomainMapper.toDomain(presupuestoIndividual);
    }
    return result;
  }

  *//**
   * Metodo para obtener la ultima foto de un presupuesto individual
   *
   * @param indAnonimizacion
   * @param indFormalizado
   *//*
  @Override
  public EntityModelPresupuestoIndividualResource findApiSnapshotIndividualBudget(String indAnonimizacion, String indFormalizado){
    log.info("Buscando presupuesto no anonimizado");
    EntityModelPresupuestoIndividualResource result = null;
    PagedModelEntityModelPresupuestoIndividualResource odlResult = presupuestosIndividualApiClient
      .findAllPresupuestoIndividual(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado), PageRequest.of(0, 1))
      .getBody();
    if (odlResult != null && !odlResult.getEmbedded().getPresupuestos().isEmpty()) {
      result = odlResult.getEmbedded().getPresupuestos().get(0);
    }
    return result;
  }*/

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
