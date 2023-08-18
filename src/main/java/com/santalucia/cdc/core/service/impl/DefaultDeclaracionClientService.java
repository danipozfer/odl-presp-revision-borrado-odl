package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.mappers.DeclaracionDomainMapper;
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


  public DefaultDeclaracionClientService(DeclaracionDomainMapper declaracionDomainMapper, PresupuestosDeclaracionesApiClient dClient,
                                         PresupuestosUtilsService pUtils, AppCustomFeaturesProperties prop) {
    this.declaracionDomainMapper = declaracionDomainMapper;

    this.declaracionApiClient = dClient;
    this.properties = prop;
    this.presupuestosUtils = pUtils;

  }

  @Override
  public List<DeclaracionDomain> findDeclarationByIdPres(String idPresupuestoODL) {
    log.info("Buscando declaraciones con idPresupuestoODL {}", idPresupuestoODL);

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
      declaraciones.addAll(declaracionDomainMapper.toDomain(result.getEmbedded().getDeclaracion()));
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
          declaraciones.addAll(declaracionDomainMapper.toDomain(result.getEmbedded().getDatosEconomicos()));
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
