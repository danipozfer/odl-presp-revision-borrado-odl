package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.client.HistoricoDeclaracionesApiClient;
import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.DeclaracionRequestBodyResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.declaraciones.api.model.PagedModelEntityModelDeclaracionResource;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.mappers.budget.HistDeclaracionDomainMapper;
import com.santalucia.cdc.core.service.HistDeclaracionClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@SuppressWarnings("NullAway")
public class DefaultHistDeclaracionClientService implements HistDeclaracionClientService {

  private static final int DEFAULT_CAPACITY = 10;
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2;

  // AutoWired


  private final HistDeclaracionDomainMapper historicoDeclaracionDomainMapper;
  private final HistoricoDeclaracionesApiClient historicoDeclaracionApiClient;//objeto que contiene la peticion get a la api
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultHistDeclaracionClientService(HistDeclaracionDomainMapper histDeclaracionDomainMapper,
                                             HistoricoDeclaracionesApiClient histDeclaracionApiClient,
                                             PresupuestosUtilsService presupuestosUtils,
                                             AppCustomFeaturesProperties properties) {
    this.historicoDeclaracionDomainMapper = histDeclaracionDomainMapper;
    this.historicoDeclaracionApiClient = histDeclaracionApiClient;
    this.presupuestosUtils = presupuestosUtils;
    this.properties = properties;
  }


  /**
   * Metodo para buscar declaraciones en el historico con idPresupuestoODL
   *
   * @param idPresupuestoODL
   * @return
   */
  @Override
  public List<DeclaracionDomain> findHistoricDeclarationByIdres(String idPresupuestoODL) {
    log.info("Buscando declaraciones historicas con idPresupuestoODL {}", idPresupuestoODL);

    int pageNum = 1;
    List<DeclaracionDomain> declarations = new ArrayList<>(DEFAULT_CAPACITY);
    PagedModelEntityModelDeclaracionResource result = historicoDeclaracionApiClient
      .findAllHistoricoDeclaraciones(presupuestosUtils.getOrSetUUID(null),
        Optional.of(getMapParamQuery(idPresupuestoODL)),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();
    boolean end = false;
    if (result != null) {
      Long maxPages = result.getPage().getTotalPages();
      declarations.addAll(historicoDeclaracionDomainMapper.toDomainsfromResources(result.getEmbedded()));
      while (pageNum < maxPages && !end) {
        result = historicoDeclaracionApiClient
          .findAllHistoricoDeclaraciones(presupuestosUtils.getOrSetUUID(null),
            Optional.of(getMapParamQuery(idPresupuestoODL)),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        } else {
          pageNum++;
          declarations.addAll(historicoDeclaracionDomainMapper.toDomainsfromResources(result.getEmbedded()));
        }
      }
    }
    return declarations;
  }

  /**
   * Metodo para actualizar una declaracion
   *
   * @param declaracion
   * @param declaracionId
   * @param uuid
   */
  @Override
  public DeclaracionDomain updateHistDeclaration(DeclaracionDomain declaracion, String declaracionId) {
    DeclaracionDomain result = null;
    if (declaracionId != null) {

      DeclaracionRequestBodyResource input = historicoDeclaracionDomainMapper.toResource(declaracion);
      result = historicoDeclaracionDomainMapper
        .toDomain(historicoDeclaracionApiClient.updateHistoricoDeclaracion(
          presupuestosUtils.getOrSetUUID(null), declaracionId, input).getBody());
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
