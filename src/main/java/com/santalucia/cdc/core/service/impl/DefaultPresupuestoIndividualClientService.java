package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.individualBudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.exception.BudgetNotFoundException;
import com.santalucia.cdc.core.mappers.budget.HistPresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.mappers.PresupuestoIndividualDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoIndividiualClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.properties.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

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
   * Metodo para la busqueda de presupuestos individuales no anonimizados
   * @param fechaAnonimizacion
   * @param indFormalizado
   * @return
   */

  @Override
  public List<PresupuestoIndividualDomain> findIndividualBudgets(Instant fechaAnonimizacion, String indFormalizado){

    log.info("Buscando presupuestos individuales no anonimizados");

    int pageNum = 1;
    List<PresupuestoIndividualDomain> presupuestosIndividuales = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.individual.api.model.PagedModelEntityModelPresupuestoColectivoResource result = presupuestosIndividualApiClient
      .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery("", "", "", fechaAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
      while (pageNum < maxPages && !end) {
        result = presupuestosIndividualApiClient
          .findAllPresupuestosIndividualUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery("", "", "", fechaAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosIndividuales.addAll(presupuestoIndividualDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
        }
      }
    }

    return presupuestosIndividuales;
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
                                                     String versPresupuestoODL, Instant fechaAnonimizacion,
                                                     String indFormalizado) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    Map<String, List<Instant>> mapDates = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    if (StringUtils.isNotBlank(idPresupuestoODL)) {
      mapParams.put("datosIdentificativos.idPresupuestoODL", List.of(idPresupuestoODL));
    }
    if (StringUtils.isNotBlank(versPresupuesto)) {
      mapParams.put("datosIdentificativos.versPresupuesto", List.of(versPresupuesto));
    }
    if (StringUtils.isNotBlank(versPresupuestoODL)) {
      mapParams.put("datosIdentificativos.versPresupuestoODL", List.of(versPresupuestoODL));
    }
    if (fechaAnonimizacion == null){
      mapDates.put("fechaYEstado.fecha.fechaAnonimizacion", null);
    }
    if (StringUtils.isNotBlank(indFormalizado)){
      mapParams.put("datosIdentificativos.indFormalizado", List.of(indFormalizado));
    }
    return mapParams;
  }
}
