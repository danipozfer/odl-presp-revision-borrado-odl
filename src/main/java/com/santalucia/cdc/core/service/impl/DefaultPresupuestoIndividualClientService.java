package com.santalucia.cdc.core.service.impl;

import com.santalucia.arq.ams.odl.presupuestos.individual.api.client.PresupuestoIndividualApiClient;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PagedModelEntityModelPresupuestoIndividualResource;
import com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PresupuestoIndividualRequestBodyResource;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.mappers.budget.PresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoIndividiualClientService;
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
public class DefaultPresupuestoIndividualClientService implements PresupuestoIndividiualClientService {
  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired
  private final PresupuestoIndividualDomainMapper presupuestoIndividualDomainMapper;
  private final PresupuestoIndividualApiClient presupuestosIndividualApiClient;

  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultPresupuestoIndividualClientService(PresupuestoIndividualDomainMapper presupuestoIndividualDomainMapper,
                                                   PresupuestoIndividualApiClient presupuestosIndividualApiClient,
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
      .findAllAdvancedPresupuestoIndividuales(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(indAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestosIndividuales()));
      while (pageNum < maxPages && !end) {
        result = presupuestosIndividualApiClient
          .findAllAdvancedPresupuestoIndividuales(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(indAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestosIndividuales()));
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
   * @param
   */
  @Override
  public PresupuestoIndividualDomain updateIndividualBudget(PresupuestoIndividualDomain individualBudget, String individualBudgetId) {
    PresupuestoIndividualDomain result = null;
    if (individualBudgetId != null) {
      PresupuestoIndividualRequestBodyResource input = presupuestoIndividualDomainMapper.toResource(individualBudget);
      result = presupuestoIndividualDomainMapper
        .toDomain(presupuestosIndividualApiClient.updatePresupuestoIndividual(
          presupuestosUtils.getOrSetUUID(null), individualBudgetId,input).getBody());
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
