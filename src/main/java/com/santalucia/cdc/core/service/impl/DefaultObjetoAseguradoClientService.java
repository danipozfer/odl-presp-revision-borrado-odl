package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import com.santalucia.cdc.core.exception.BudgetNotFoundException;
import com.santalucia.cdc.core.mappers.HistObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.mappers.ObjetoAseguradoDomainMapper;
import com.santalucia.cdc.core.service.ObjetoAseguradoClientService;
import com.santalucia.cdc.core.service.PresupuestosUtilsService;
import com.santalucia.cdc.properties.reload.AppCustomFeaturesProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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
  private final HistObjetoAseguradoDomainMapper histObjetoAseguradoDomainMapper;
  private final PresupuestosObjetoAseguradoApiClient objetoAseguradoApiClient;
  private final HistoricoPresupuestosObjetosAseguradosApiClient histObjetoAseguradoApiClient;
  private final PresupuestosUtilsService presupuestosUtils;
  private final AppCustomFeaturesProperties properties;

  public DefaultObjetoAseguradoClientService(ObjetoAseguradoDomainMapper oMapper,
                                             HistObjetoAseguradoDomainMapper hMapper,
                                             PresupuestosObjetoAseguradoApiClient oClient,
                                             HistoricoPresupuestosObjetosAseguradosApiClient hClient,
                                             PresupuestosUtilsService pUtils,
                                             AppCustomFeaturesProperties prop) {
    this.objetoAseguradoDomainMapper = oMapper;
    this.histObjetoAseguradoDomainMapper = hMapper;
    this.objetoAseguradoApiClient = oClient;
    this.histObjetoAseguradoApiClient = hClient;
    this.presupuestosUtils = pUtils;
    this.properties = prop;
  }

  /**
   * Metodo para insertar un nuevo objeto asegurado
   *
   * @param objetosAsegurados
   */
  @Override
  public void insertSecuredObject(ObjetosAseguradosDomain objetosAsegurados) {
    PresupuestosObjetoAseguradoRequestBodyResource input = objetoAseguradoDomainMapper.toResource(objetosAsegurados);
    UUID uuid = presupuestosUtils.getOrSetUUID(null);
    insertAsyncSecuredObject(input, uuid);
  }

  /**
   *
   * @param input
   * @param uuid
   * @return
   */
  @Async
  private CompletableFuture<Void> insertAsyncSecuredObject(final PresupuestosObjetoAseguradoRequestBodyResource input,
                                                         final UUID uuid) {
    return CompletableFuture
      .runAsync(() -> objetoAseguradoApiClient.savePresupuestosObjetoAseguradoUsingPOST(uuid,
        input, Optional.empty(), Optional.empty()));
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
        getMapParamQuery(idObjetoAseguradoODL, "", ""), PageRequest.of(0, 1))
      .getBody();
    if (odlResult != null && !odlResult.getEmbedded().getDeclaracion().isEmpty()) {
      result = odlResult.getEmbedded().getDeclaracion().get(0);
    }
    return result;
  }

  /**
   * Metodo para insertar en historico un objeto asegurado
   * @param objetoAsegurado
   */
  @Override
  public ObjetosAseguradosDomain insertHistoricSecuredObject(ObjetosAseguradosDomain objetoAsegurado) {
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.ObjetoAseguradoRequestBodyResource input = histObjetoAseguradoDomainMapper
      .toResource(objetoAsegurado);
    return histObjetoAseguradoDomainMapper.toDomain(histObjetoAseguradoApiClient
      .savePresupuestosObjetoAseguradoUsingPOST(presupuestosUtils.getOrSetUUID(null), input, Optional.empty(), Optional.empty()).getBody());
  }

  /**
   * Metodo para buscar un objeto asegurado en la coleccion de historico
   *
   * @param idObjetoAseguradoODL
   * @param numVersObjetoAsegurado
   */
  @Override
  public List<ObjetosAseguradosDomain> findAllHistoricSecuredObject(String idObjetoAseguradoODL, String numVersObjetoAsegurado) {
    log.info("Buscando objetos asegurados historicos con idObjetoAseguradoODL {} y numVersObjetoAsegurado {}", idObjetoAseguradoODL,
      numVersObjetoAsegurado);

    int pageNum = 1;
    List<ObjetosAseguradosDomain> objetosAsegurados = new ArrayList<>(DEFAULT_CAPACITY);
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelObjetoAseguradoResource result = histObjetoAseguradoApiClient
      .findAllPresupuestosObjetoAseguradoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idObjetoAseguradoODL, numVersObjetoAsegurado, ""),
        PageRequest.of(0, this.properties.getFindallPageSize()))
      .getBody();

    boolean end = false;
    if(result != null) {
      Long maxPages = result.getPage().getTotalPages();
      objetosAsegurados.addAll(histObjetoAseguradoDomainMapper.toDomainsfromResources(result.getEmbedded().getDeclaracion()));
      while (pageNum < maxPages && !end) {
        result = histObjetoAseguradoApiClient
          .findAllPresupuestosObjetoAseguradoUsingGET(presupuestosUtils.getOrSetUUID(null),
            getMapParamQuery(idObjetoAseguradoODL, numVersObjetoAsegurado, ""),
            PageRequest.of(pageNum, this.properties.getFindallPageSize()))
          .getBody();
        if (result == null) {
          end = true;
        }else {
          pageNum++;
          objetosAsegurados.addAll(histObjetoAseguradoDomainMapper.toDomainsfromResources(result.getEmbedded().getDatosEconomicos()));
        }
      }
    }

    return objetosAsegurados;
  }

  /**
   * Metodo para hacer rollback de un objeto asegurado en el historico
   *
   * @param idObjetoAseguradoODL
   * @param numVersMovObjetoAseguradoODL
   * @param uuid
   */
  @Override
  public void rollbackSecuredObject(String idObjetoAseguradoODL, String numVersMovObjetoAseguradoODL, UUID uuid) {
    String securedObjectId = findApiIdHistoricSecuredObject(idObjetoAseguradoODL, numVersMovObjetoAseguradoODL);
    if (securedObjectId == null) {
      log.error("Objeto asegurado no encontrado. idObjetoAseguradoODL {}, numVersMovObjetoAseguradoODL: {}", idObjetoAseguradoODL, numVersMovObjetoAseguradoODL);
      throw new BudgetNotFoundException(idObjetoAseguradoODL);
    }
    histObjetoAseguradoApiClient.deletePresupuestosObjetoAseguradoUsingDELETE(securedObjectId, uuid, Optional.empty(), Optional.empty());
  }

  /**
   * Metodo para buscar un objeto asegurado en el historico
   *
   * @param idObjetoAseguradoODL
   * @param numVersObjetoAseguradoODL
   */
  @Override
  public String findApiIdHistoricSecuredObject(String idObjetoAseguradoODL, String numVersObjetoAseguradoODL) {
    log.info("Buscando objeto asegurado historico con idObjetoAseguradoODL {} y numVersObjetoAseguradoODL {}", idObjetoAseguradoODL,
      numVersObjetoAseguradoODL);
    String resultId = null;
    com.santalucia.arq.ams.odl.historico.presupuestos.declaracion.api.model.PagedModelEntityModelDeclaracionsResource result = histObjetoAseguradoApiClient
      .findAllPresupuestosObjetoAseguradoUsingGET(presupuestosUtils.getOrSetUUID(null),
        getMapParamQuery(idObjetoAseguradoODL, "", numVersObjetoAseguradoODL), PageRequest.of(0, 1))
      .getBody();

    if (result != null && !result.getEmbedded().getObjetoAsegurado().isEmpty()) {
      resultId = result.getEmbedded().getObjetoAsegurado().get(0).getId();
    }

    return resultId;
  }

  /**
   * Metodo para obtener parametros para la consulta
   *
   * @param idObjAsegODL
   * @param numVersObjetoAsegurado
   * @param numVersObjetoAseguradoODL
   *
   * @return
   */
  private Map<String, List<String>> getMapParamQuery(String idObjAsegODL, String numVersObjetoAsegurado,
                                                     String numVersObjetoAseguradoODL) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);
    if (StringUtils.isNotBlank(idObjAsegODL)) {
      mapParams.put("datosIdentificativos.idObjAsegODL", List.of(idObjAsegODL));
    }
    if (StringUtils.isNotBlank(numVersObjetoAsegurado)) {
      mapParams.put("datosIdentificativos.numVersObjetoAsegurado", List.of(numVersObjetoAsegurado));
    }
    if (StringUtils.isNotBlank(numVersObjetoAseguradoODL)) {
      mapParams.put("datosIdentificativos.numVersObjetoAseguradoODL", List.of(numVersObjetoAseguradoODL));
    }
    return mapParams;
  }
}
