package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
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
  private final ObjetoAseguradoApiClient objetoAseguradoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;


  public DefaultObjetoAseguradoClientService(ObjetoAseguradoDomainMapper objetoAseguradoDomainMapper,
                                             ObjetoAseguradoApiClient objetoAseguradoApiClient,
                                             PresupuestosUtilsService presupuestosUtils,
                                             AppCustomFeaturesProperties properties) {
    this.objetoAseguradoDomainMapper = objetoAseguradoDomainMapper;
    this.objetoAseguradoApiClient = objetoAseguradoApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }



  /**
   * Metodo para obtener un objeto asegurado
   *
   * @param idObjetoAseguradoODL
   */
  @Override
  public ObjetosAseguradosDomain getSecuredObject(String idObjetoAseguradoODL) {
    ObjetosAseguradosDomain result = null;
    EntityModelObjetoAseguradoResource objetoAsegurado = findApiSnapshotSecuredObject(idObjetoAseguradoODL);
    if (objetoAsegurado != null) {
      result = objetoAseguradoDomainMapper.toDomain(objetoAsegurado);
    }
    return result;
  }

  /**
   * Metodo para obtener la ultima foto de un objeto asegurado
   *
   * @param idObjetoAseguradoODL
   */
  @Override
  public EntityModelObjetoAseguradoResource findApiSnapshotSecuredObject(String idObjetoAseguradoODL) {
    log.info("Buscando objeto asegurado con idObjetoAseguradoODL {}", idObjetoAseguradoODL);
    EntityModelObjetoAseguradoResource result = null;
    PagedModelEntityModelObjetoAseguradoResource odlResult = objetoAseguradoApiClient
      .findAllPresupuestosObjetoAseguradoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idObjetoAseguradoODL), PageRequest.of(0, 1))
      .getBody();
    if (odlResult != null && !odlResult.getEmbedded().getDeclaracion().isEmpty()) {
      result = odlResult.getEmbedded().getDeclaracion().get(0);
    }
    return result;
  }

  /**
   * Metodo para actualizar un objeto asegurado
   *
   * @param securedObject
   * @param securedObjectId
   * @param uuid
   */
  @Override
  public ObjetosAseguradosDomain updateSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId, UUID uuid) {
    ObjetosAseguradosDomain result = null;
    if (securedObjectId != null) {
      PresupuestosObjetoAseguradoRequestBodyResource input = objetoAseguradoDomainMapper.toResource(securedObject);
      result = objetoAseguradoDomainMapper
        .toDomain(objetoAseguradoApiClient.savePresupuestosObjetoAseguradoUsingPUT(securedObjectId,
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
