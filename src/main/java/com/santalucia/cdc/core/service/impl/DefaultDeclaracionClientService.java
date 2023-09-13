package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.mappers.budget.DeclaracionDomainMapper;
import com.santalucia.cdc.core.mappers.budget.HistDeclaracionDomainMapper;
import com.santalucia.cdc.core.service.DeclaracionClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DefaultDeclaracionClientService implements DeclaracionClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired

  private final DeclaracionDomainMapper declaracionDomainMapper;
  private final PresupuestosDeclaracionesApiClient declaracionApiClient;//objeto que contiene la peticion get a la api
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;


  public DefaultDeclaracionClientService(DeclaracionDomainMapper declaracionDomainMapper,
                                         PresupuestosDeclaracionesApiClient declaracionApiClient,
                                         PresupuestosUtilsService presupuestosUtils,
                                         AppCustomFeaturesProperties properties) {
    this.declaracionDomainMapper = declaracionDomainMapper;
    this.declaracionApiClient = declaracionApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }

  /**
   * Metodo para buscar declaraciones en ultima foto con idPresupuestoOdl
   * @param idPresupuestoODL
   * @return
   */
  @Override
  public List<DeclaracionDomain> findDeclarationByIdPres(String idPresupuestoODL) {
    log.info("Buscando declaraciones con idPresupuestoODL {}", idPresupuestoODL);

    int pageNum = 1;
    List<DeclaracionDomain> declaraciones = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelDeclaracionResource result = declaracionApiClient
      .findAllPresupuestosDeclaracionUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if (result != null) {
      Long maxPages = result.getPage().getTotalPages();//busca las declaraciones por id
      declaraciones.addAll(histDeclaracionDomainMapper.toDomain(result.getEmbedded().getDeclaracion()));
      while (pageNum < maxPages && !end) {
        result = declaracionApiClient
          .findAllPresupuestosDeclaracionUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        } else {
          pageNum++;//añade las declaraciones encontradas
          declaraciones.addAll(histDeclaracionDomainMapper.toDomain(result.getEmbedded().getDeclaracion()));
        }
      }
    }

    return declaraciones;
  }


  /**
   * Metodo para actualizar una declaracion
   *
   * @param declaracion
   * @param declaracionId
   * @param uuid
   */
  @Override
  public DeclaracionDomain updateDeclaration(DeclaracionDomain declaracion, String declaracionId, UUID uuid) {
    DeclaracionDomain result = null;
    if (declaracionId != null) {
      PresupuestosDeclaracionesRequestBodyResource input = declaracionDomainMapper.toResource(declaracion);
      result = declaracionDomainMapper
        .toDomain(declaracionApiClient.savePresupuestosDeclaracionUsingPUT(declaracionId,
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
