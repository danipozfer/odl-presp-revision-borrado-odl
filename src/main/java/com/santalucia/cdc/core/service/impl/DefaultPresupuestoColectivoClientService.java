package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.exception.BudgetNotFoundException;
import com.santalucia.cdc.core.mappers.HistPresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.mappers.PresupuestoColectivoDomainMapper;
import com.santalucia.cdc.core.service.PresupuestoColectivoClientService;
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
public class DefaultPresupuestoColectivoClientService implements PresupuestoColectivoClientService {
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
   * Metodo para la busqueda de presupuestos colectivos no anonimizados
   * @param fechaAnonimizacion
   * @param indFormalizado
   * @return
   */

  @Override
  public List<PresupuestoColectivoDomain> findCollectiveBudgets(Instant fechaAnonimizacion, String indFormalizado){

    log.info("Buscando presupuestos colectivos no anonimizados");

    int pageNum = 1;
    List<PresupuestoColectivoDomain> presupuestosColectivos = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.colectivo.api.model.PagedModelEntityModelPresupuestoColectivoResource result = presupuestosColectivoApiClient
      .findAllPresupuestosColectivoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery("", "", "", fechaAnonimizacion, indFormalizado),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      presupuestosColectivos.addAll(presupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
      while (pageNum < maxPages && !end) {
        result = presupuestosColectivoApiClient
          .findAllPresupuestosColectivoUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery("", "", "", fechaAnonimizacion, indFormalizado),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          presupuestosColectivos.addAll(presupuestoColectivoDomainMapper.toDomainsfromResources(result.getEmbedded().getPresupuestoColectivo()));
        }
      }
    }

    return presupuestosColectivos;
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
