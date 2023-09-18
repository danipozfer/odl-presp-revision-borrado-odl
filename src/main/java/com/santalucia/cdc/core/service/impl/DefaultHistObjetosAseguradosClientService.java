package com.santalucia.cdc.core.service.impl;

import com.nimbusds.oauth2.sdk.util.StringUtils;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.client.HistoricoObjetoAseguradoPresupuestoApiClient;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.ObjetoAseguradoPresupuestoRequestBodyResource;
import com.santalucia.arq.ams.odl.historico.presupuestos.objeto.asegurado.api.model.PagedModelEntityModelObjetoAseguradoPresupuestoResource;
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
  private final HistObjetoAseguradoDomainMapper historicoObjetoAseguradoDomainMapper;
  private final HistoricoObjetoAseguradoPresupuestoApiClient historicoObjetoAseguradoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultHistObjetosAseguradosClientService(HistObjetoAseguradoDomainMapper histObjetoAseguradoDomainMapper,
                                                   HistoricoObjetoAseguradoPresupuestoApiClient historicoObjetoAseguradoApiClient,
                                                   PresupuestosUtilsService presupuestosUtils,
                                                   AppCustomFeaturesProperties properties) {
    this.historicoObjetoAseguradoDomainMapper = histObjetoAseguradoDomainMapper;
    this.historicoObjetoAseguradoApiClient = historicoObjetoAseguradoApiClient;
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
    PagedModelEntityModelObjetoAseguradoPresupuestoResource result = historicoObjetoAseguradoApiClient
      .findAllAdvancedHistoricoObjetoAseguradoPresupuesto(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idPresupuestoODL),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      objetosAsegurados.addAll(historicoObjetoAseguradoDomainMapper.toDomainsfromResources(result.getEmbedded()));
      while (pageNum < maxPages && !end) {
        result = historicoObjetoAseguradoApiClient
          .findAllAdvancedHistoricoObjetoAseguradoPresupuesto(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idPresupuestoODL),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          objetosAsegurados.addAll(historicoObjetoAseguradoDomainMapper.toDomainsfromResources(result.getEmbedded()));
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
      ObjetoAseguradoPresupuestoRequestBodyResource input = historicoObjetoAseguradoDomainMapper.toResource(securedObject);
      result = historicoObjetoAseguradoDomainMapper
        .toDomain(historicoObjetoAseguradoApiClient.updateHistoricoObjetoAseguradoPresupuesto(
          presupuestosUtils.getOrSetUUID(null),securedObjectId, input).getBody());
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
