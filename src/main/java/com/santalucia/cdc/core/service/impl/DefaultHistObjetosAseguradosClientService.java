package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.mappers.budget.HistObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.service.HistObjetosAseguradosClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DefaultHistObjetosAseguradosClientService implements HistObjetosAseguradosClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired
  private final HistObjetoAseguradoDomainMapper histObjetoAseguradoDomainMapper;
  private final HistObjetoAseguradoApiClient histObjetoAseguradoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultHistObjetosAseguradosClientService(HistObjetoAseguradoDomainMapper histObjetoAseguradoDomainMapper,
                                                   HistObjetoAseguradoApiClient histObjetoAseguradoApiClient,
                                                   PresupuestosUtilsService presupuestosUtils,
                                                   AppCustomFeaturesProperties properties) {
    this.histObjetoAseguradoDomainMapper = histObjetoAseguradoDomainMapper;
    this.histObjetoAseguradoApiClient = histObjetoAseguradoApiClient;
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



  /**
   * Metodo para actualizar un objeto asegurado en histórico
   *
   * @param securedObject
   * @param securedObjectId
   * @param uuid
   */
  @Override
  public ObjetosAseguradosDomain updateHistSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId, UUID uuid) {
    ObjetosAseguradosDomain result = null;
    if (securedObjectId != null) {
      PresupuestosObjetoAseguradoRequestBodyResource input = histObjetoAseguradoDomainMapper.toResource(securedObject);
      result = histObjetoAseguradoDomainMapper
        .toDomain(histObjetoAseguradoApiClient.savePresupuestosObjetoAseguradoUsingPUT(securedObjectId,
          presupuestosUtils.getOrSetUUID(uuid), input, Optional.empty(), Optional.empty()).getBody());
    }
    return result;
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
