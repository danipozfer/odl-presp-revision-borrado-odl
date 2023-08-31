package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import com.santalucia.cdc.core.mappers.insurance.HistPolizaColectivaDomainMapper;
import com.santalucia.cdc.core.mappers.insurance.PolizaColectivaDomainMapper;
import com.santalucia.cdc.core.service.PolizaColectivaClientService;
import com.santalucia.cdc.core.service.PolizaUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Cliente general para las distintas APIs CRUD de polizas
 *
 * @author Nfq
 *
 */
@Slf4j
@Service
public class DefaultPolizaColectivaClientService implements PolizaColectivaClientService {

  private final PolizaColectivaDomainMapper colectivosMapper;
  private final HistPolizaColectivaDomainMapper historicoColectivosMapper;
  private final PolizasColectivasApiClient colectivasApiClient;
  private final HistoricoPolizasColectivasApiClient historicoColectivasApiClient;
  private final PolizaUtilsService polizaUtils;

  // Tamanyo maximo de pagina para evitar multiples llamadas si no es necesario
  private static final int FINDALL_PAGE_SIZE = 500;
  private static final int DEFAULT_CAPACITY = 10; // Capacidad inicial por defecto
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2; // aka 4

  public DefaultPolizaColectivaClientService(PolizaColectivaDomainMapper colectivosMapper,
                                             HistPolizaColectivaDomainMapper historicoColectivosMapper,
                                             PolizasColectivasApiClient colectivasApiClient,
                                             HistoricoPolizasColectivasApiClient historicoColectivasApiClient,
                                             PolizaUtilsService polizaUtils) {
    this.colectivosMapper = colectivosMapper;
    this.historicoColectivosMapper = historicoColectivosMapper;
    this.colectivasApiClient = colectivasApiClient;
    this.historicoColectivasApiClient = historicoColectivasApiClient;
    this.polizaUtils = polizaUtils;
  }

  /**
   * Obtiene una poliza en base a su numero de poliza y certificado si es
   * necesario
   *
   * @param numIdPresupuesto
   * @param uuid   UUID a utilizar o null para generar uno nuevo
   * @return la poliza o null si no existe la poliza
   */
  @Override
  public PolizaDomain getPolizaColectiva(String numIdPresupuesto, UUID uuid) {
    PolizaDomain result = null;
    String polizaApiId = findApiIdUltimaFotoColectiva(numIdPresupuesto);

    if (polizaApiId != null) {
      result = colectivosMapper.toDomain(colectivasApiClient
        .findByIdPolizaColectivaUsingGET(polizaApiId, polizaUtils.getOrSetUUID(uuid)).getBody());
    }

    return result;
  }

  /**
   * Obtiene el id unico de una poliza en base a su numIdPresupuesto de la coleccion de ultima foto
   *
   * @param numIdPresupuesto equivalente a idPresupuestoOrigen
   * @return id unico de poliza o null si no existe la poliza
   */
  @Override
  public String findApiIdUltimaFotoColectiva(String numIdPresupuesto) {
    String resultID = null;
    log.debug("La poliza es colectiva, se busca con numIdPresupuesto {} ",
      numIdPresupuesto);
    UUID uuid = polizaUtils.getOrSetUUID(null);
    Map<String, List<String>> paramQuery = getMapParamQuery("", "", "",
      numIdPresupuesto);

    CollectionModelPolizaColectivaResource result = colectivasApiClient
      .findAllPolizaColectivaUsingGET(uuid, paramQuery, PageRequest.of(0, 1)).getBody();

    if (result == null || result.getEmbedded().getPolizasColectivas().isEmpty()) {
      log.debug("No se encuentra la poliza colectiva");
      resultID = null;
    } else {
      resultID = result.getEmbedded().getPolizasColectivas().get(0).getId();
    }
    return resultID;
  }

  /**
   * Obtiene todas las polizas del historico en base a numero de poliza y
   * certificado si es necesario
   *
   * @param poliza poliza individual/colectiva/certificado
   * @param uuid   UUID a utilizar o null para generar uno nuevo
   * @return listado de fotos del historico o null si no existe la poliza
   */
  @Override
  public List<PolizaDomain> findAllHistoricoColectiva(String numIdPresupuesto, UUID uuid) {
    int pageNum = 0;
    int maxPages = -1;

    List<PolizaDomain> polizas = new ArrayList<>(DEFAULT_CAPACITY);
    Map<String, List<String>> paramQuery = getMapParamQuery("", "",
      "", numIdPresupuesto);
    boolean end = false;
    while (pageNum > maxPages && maxPages != 0 && !end) {
      log.debug("Extrayendo pagina {} historico para polizas", pageNum + 1);
      CollectionModelHistoricoPolizaColectivaResource result = historicoColectivasApiClient
        .findAllPolizaColectivaUsingGET(polizaUtils.getOrSetUUID(null), paramQuery,
          PageRequest.of(pageNum, FINDALL_PAGE_SIZE))
        .getBody();

      if (result == null) {
        polizas = null;
        end = true;
      }else {
        pageNum++;
        maxPages = result.getPage().getTotalPages();

        log.debug("Se encontraron {} resultados en {} paginas", result.getEmbedded().getPolizasColectivas().size(),
          maxPages);

        polizas.addAll(
          historicoColectivosMapper.toDomainsfromResources(result.getEmbedded().getPolizasColectivas()));
      }
    }
    if(polizas != null) {
      log.debug("Total resultados paginados: {}", polizas.size());
    }
    return polizas;
  }


  private Map<String, List<String>> getMapParamQuery(String idPolizaODL, String versPoliza,
                                                     String numCertificado, String numIdPresupuesto) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);

    if (StringUtils.isNotBlank(idPolizaODL)) {
      mapParams.put("datosIdentificativos.idPolizaODL", List.of(idPolizaODL));
    }
    if (StringUtils.isNotBlank(versPoliza)) {
      mapParams.put("movimientos.versPolizaODL", List.of(versPoliza));
    }
    if (StringUtils.isNotBlank(numCertificado)) {
      mapParams.put("datosIdentificativos.numCertificado", List.of(numCertificado));
    }
    if (StringUtils.isNotBlank(numIdPresupuesto)) {
      mapParams.put("datosIdentificativos.numIdPresupuesto", List.of(numIdPresupuesto));
    }
    return mapParams;
  }
}
