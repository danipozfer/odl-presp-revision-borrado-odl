package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.mappers.ObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.service.ObjetoAseguradoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class DefaultObjetoAseguradoClientService implements ObjetoAseguradoClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired

  private final ObjetoAseguradoDomainMapper objetoAseguradoDomainMapper;
  private final ObjetoAseguradoApiClient objetoAseguradoApiClient;//objeto que contiene la peticion get a la api
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;


  public DefaultObjetoAseguradoClientService(ObjetoAseguradoDomainMapper objetoAseguradoDomainMapper, ObetoAseguradoApiClient obetoAseguradoApiClient,
                                         PresupuestosUtilsService pUtils, AppCustomFeaturesProperties prop) {
    this.objetoAseguradoDomainMapper = objetoAseguradoDomainMapper;
    this.objetoAseguradoApiClient = dClient;
    this.properties = prop;
    this.presupuestosUtils = pUtils;

  }

  @Override
  public List<DeclaracionDomain> findObjetoAseguradoByIdPres(String idPresupuestoODL) {
    log.info("Buscando objetoAsegurado con idPresupuestoODL {}", idPresupuestoODL);

    int pageNum = 1;
    List<DeclaracionDomain> declaraciones = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.presupuestos.declaracion.api.model.PagedModelEntityModelDeclaracionResource result = declaracionApiClient
      .findAllPresupuestosDeclaracionUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if (result != null) {
      Long maxPages = result.getPage().getTotalPages();//busca las declaraciones por id
      declaraciones.addAll(objetoAseguradoDomainMapper.toDomain(result.getEmbedded().getDeclaracion()));
      while (pageNum < maxPages && !end) {
        result = objetoAseguradoApiClient
          .findAllPresupuestosDeclaracionUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        } else {
          pageNum++;//añade las declaraciones encontradas
          declaraciones.addAll(objetoAseguradoDomainMapper.toDomain(result.getEmbedded().getDatosEconomicos()));
        }
      }
    }

    return declaraciones;
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
