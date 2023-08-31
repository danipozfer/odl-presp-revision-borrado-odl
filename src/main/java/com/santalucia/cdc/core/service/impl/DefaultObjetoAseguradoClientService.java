package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.mappers.budget.HistObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.mappers.budget.ObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.service.ObjetoAseguradoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DefaultObjetoAseguradoClientService implements ObjetoAseguradoClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired

  private final ObjetoAseguradoDomainMapper objetoAseguradoDomainMapper;
  private final HistObjetoAseguradoDomainMapper histObjetoAseguradoDomainMapper;
  private final ObjetoAseguradoApiClient objetoAseguradoApiClient;//objeto que contiene la peticion get a la api
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;


  public DefaultObjetoAseguradoClientService(ObjetoAseguradoDomainMapper objetoAseguradoDomainMapper,
                                             HistObjetoAseguradoDomainMapper histObjetoAseguradoDomainMapper,
                                             ObjetoAseguradoApiClient objetoAseguradoApiClient,
                                             PresupuestosUtilsService presupuestosUtils,
                                             AppCustomFeaturesProperties properties) {
    this.objetoAseguradoDomainMapper = objetoAseguradoDomainMapper;
    this.histObjetoAseguradoDomainMapper = histObjetoAseguradoDomainMapper;
    this.objetoAseguradoApiClient = objetoAseguradoApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para buscar un objeto asegurado en la coleccion de historico
   *
   * @param idPresupuestoODL
   */
  @Override
  public List<ObjetosAseguradosDomain> findAllHistoricSecuredObject(String idPresupuestoODL) {
    log.info("Buscando objetos asegurados historicos con idPresupuestoODL {} ", idPresupuestoODL);

    int pageNum = 1;
    List<ObjetosAseguradosDomain> objetosAsegurados = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelObjetoAseguradoResource result = histObjetoAseguradoApiClient
      .findAllPresupuestosObjetoAseguradoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      objetosAsegurados.addAll(histObjetoAseguradoDomainMapper.toDomainsfromResources(result.getEmbedded().getObjetosAsegurados()));
      while (pageNum < maxPages && !end) {
        result = histObjetoAseguradoApiClient
          .findAllPresupuestosObjetoAseguradoUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          objetosAsegurados.addAll(histObjetoAseguradoDomainMapper.toDomainsfromResources(result.getEmbedded().getObjetosAsegurados()));
        }
      }
    }

    return objetosAsegurados;
  }

  @Override
  public List<DeclaracionDomain> findObjetoAseguradoByIdPres(String idPresupuestoODL) {
    log.info("Buscando objetoAsegurado con idPresupuestoODL {}", idPresupuestoODL);

    int pageNum = 1;
    List<DeclaracionDomain> objetosAsegurados = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.declaracion.api.model.PagedModelEntityModelDeclaracionResource result = objetoAseguradoApiClient
      .findAllPresupuestosObjetoAseguradoGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if (result != null) {
      Long maxPages = result.getPage().getTotalPages();//busca las declaraciones por id
      objetosAsegurados.addAll(objetoAseguradoDomainMapper.toDomain(result.getEmbedded().getDeclaracion()));
      while (pageNum < maxPages && !end) {
        result = objetoAseguradoApiClient
          .findAllPresupuestosObjetoAseguradoGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        } else {
          pageNum++;//añade las declaraciones encontradas
          objetosAsegurados.addAll(objetoAseguradoDomainMapper.toDomain(result.getEmbedded().getDatosEconomicos()));
        }
      }
    }

    return objetosAsegurados;
  }

  /**
   * Metodo para obtener parametros para la consulta
   *
   * @param idPresupuestoODL
   * @return
   */

  private Map<String, List<String>> getMapParamQuery(String idPresupuestoODL) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    if (StringUtils.isNotBlank(idPresupuestoODL)) {
      mapParams.put("datosIdentificativos.idPresupuestoODL", List.of(idPresupuestoODL));
    }
    return mapParams;
  }
}
