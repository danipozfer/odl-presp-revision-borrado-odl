package com.santalucia.cdc.core.service.impl.policies;

import com.santalucia.arq.ams.odl.historico.polizas.individuales.api.client.HistoricoPolizasIndividualesApiClient;
import com.santalucia.arq.ams.odl.historico.polizas.individuales.api.model.CollectionModelHistoricoPolizaIndividualCertificadoResource;
import com.santalucia.arq.ams.odl.polizas.individuales.api.client.PolizasIndividualesApiClient;
import com.santalucia.arq.ams.odl.polizas.individuales.api.model.CollectionModelPolizaIndividualCertificadoResource;
import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import com.santalucia.cdc.core.mappers.insurance.HistPolizaIndividualDomainMapper;
import com.santalucia.cdc.core.mappers.insurance.PolizaIndividualDomainMapper;
import com.santalucia.cdc.core.service.policies.PolizaIndividualClientService;
import com.santalucia.cdc.core.service.policies.PolizaUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class DefaultPolizaIndividualClientService implements PolizaIndividualClientService {
  private final PolizaIndividualDomainMapper individualMapper;
  private final HistPolizaIndividualDomainMapper historicoIndividualMapper;
  private final PolizasIndividualesApiClient individualesApiClient;
  private final HistoricoPolizasIndividualesApiClient historicoIndividualesApiClient;
  private final PolizaUtilsService polizaUtils;

  // Tamanyo maximo de pagina para evitar multiples llamadas si no es necesario
  private static final int FINDALL_PAGE_SIZE = 500;
  private static final int DEFAULT_CAPACITY = 10; // Capacidad inicial por defecto
  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2; // aka 4

  /**
   * Constructor de clase
   *
   * @param iMapper           Mapper de individuales
   * @param hIndividualMapper Mapper historico individuales
   * @param iApiClient        Cliente api individuales
   * @param hiApiClient       Cliente api historico individuales
   * @param pUtils            Servicio util poliza
   */
  public DefaultPolizaIndividualClientService(PolizaIndividualDomainMapper iMapper,
                                                  HistPolizaIndividualDomainMapper hIndividualMapper, PolizasIndividualesApiClient iApiClient,
                                                  HistoricoPolizasIndividualesApiClient hiApiClient, PolizaUtilsService pUtils) {
    this.individualMapper = iMapper;
    this.historicoIndividualMapper = hIndividualMapper;
    this.individualesApiClient = iApiClient;
    this.historicoIndividualesApiClient = hiApiClient;
    this.polizaUtils = pUtils;
  }

  /**
   * Obtiene una poliza en base a su numero de poliza y certificado si es
   * necesario
   *
   * @param numIdPresupuesto id equivalente a idPresupuestoOrigen
   * @param uuid   UUID a utilizar o null para generar uno nuevo
   * @return la poliza o null si no existe la poliza
   */
  @Override
  public PolizaDomain getPolizaIndividual(String numIdPresupuesto, UUID uuid) {
    PolizaDomain result = null;
    String polizaApiId = findApiIdUltimaFotoIndividual(numIdPresupuesto);
    if (polizaApiId != null) {
      result = individualMapper.toDomain(individualesApiClient
        .findByIdPolizaUsingGET(polizaApiId, polizaUtils.getOrSetUUID(uuid)).getBody());
      log.debug("FecAlta de getIndividualesApiClient {}",
        result.getFechasYEstados().getFechas().getFecAltaPoliza());
    }

    return result;
  }

  /**
   * Obtiene el id unico de una poliza en base a su numIdPresupuesto de la coleccion de ultima foto
   *
   * @param numIdPresupuesto id equivalente a idPresupuestoOrigen
   * @return id unico de poliza o null si no existe la poliza
   */
  @Override
  public String findApiIdUltimaFotoIndividual(String numIdPresupuesto) {
    String resultID = null;
    log.debug("Recuperar ultima foto - La poliza NO es colectiva, se busca con numIdPresupuesto {}", numIdPresupuesto);
    Map<String, List<String>> paramQuery = getMapParamQuery("", "",
      "", numIdPresupuesto);
    UUID uuid = polizaUtils.getOrSetUUID(null);

    // Unicamente necesitamos la primera poliza
    CollectionModelPolizaIndividualCertificadoResource result = individualesApiClient
      .findAllPolizaUsingGET(uuid, paramQuery, PageRequest.of(0, 1)).getBody();

    if (result == null || result.getEmbedded().getPolizasIndividuales().isEmpty()) {
      log.debug("No se encuentra la poliza individual");
      resultID = null;
    } else {
      log.info("Ultima foto recuperada.");
      resultID = result.getEmbedded().getPolizasIndividuales().get(0).getId();
    }
    return resultID;
  }


  /**
   * Obtiene todas las polizas del historico por numIdPresupuesto
   *
   * @param numIdPresupuesto id equivalente a idPresupuestoOrigen
   * @param uuid   UUID a utilizar o null para generar uno nuevo
   * @return listado de fotos del historico o null si no existe la poliza
   */
  @Override
  public List<PolizaDomain> findAllHistoricoIndividual(String numIdPresupuesto, UUID uuid) {
    int pageNum = 0;
    int maxPages = -1;

    List<PolizaDomain> polizas = new ArrayList<>(DEFAULT_CAPACITY);
    boolean end = false;
    while (pageNum > maxPages && maxPages != 0 && !end) {
      log.debug("Extrayendo pagina {} historico ", pageNum + 1);

      CollectionModelHistoricoPolizaIndividualCertificadoResource result = historicoIndividualesApiClient
        .findAllHistoricoPolizaUsingGET(polizaUtils.getOrSetUUID(null),
          getMapParamQuery("", "",
            "", numIdPresupuesto),
          PageRequest.of(pageNum, FINDALL_PAGE_SIZE))
        .getBody();

      if (result == null) {
        polizas = null;
        end = true;
      }else {
        pageNum++;
        maxPages = result.getPage().getTotalPages();

        log.debug("Se encontraron {} resultados en {} paginas",
          result.getEmbedded().getPolizasIndividuales().size(), maxPages);

        polizas.addAll(
          historicoIndividualMapper.toDomainsfromResources(result.getEmbedded().getPolizasIndividuales()));
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
