package com.santalucia.cdc.core.service.impl.policies;

import com.santalucia.arq.ams.odl.historico.polizas.individuales.api.client.HistoricoPolizasIndividualesApiClient;
import com.santalucia.arq.ams.odl.historico.polizas.individuales.api.model.CollectionModelHistoricoPolizaIndividualCertificadoResource;
import com.santalucia.arq.ams.odl.polizas.individuales.api.client.PolizasIndividualesApiClient;
import com.santalucia.arq.ams.odl.polizas.individuales.api.model.CollectionModelPolizaIndividualCertificadoResource;
import com.santalucia.cdc.core.service.policies.PolizaIndividualClientService;
import com.santalucia.cdc.core.service.policies.PolizaUtilsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class DefaultPolizaIndividualClientService implements PolizaIndividualClientService {

  private final PolizasIndividualesApiClient individualesApiClient;
  private final HistoricoPolizasIndividualesApiClient historicoIndividualesApiClient;
  private final PolizaUtilsService polizaUtils;

  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2; // aka 4

  /**
   * Comprueba si existe una poliza individual en ultima foto
   * con ese numIdPresupuesto
   * @param numIdPresupuesto
   * @return
   */
  @Override
  public boolean getPolizaIndividual(String numIdPresupuesto) {
    boolean resultBool = false;
    log.debug("La poliza individual, se busca con numIdPresupuesto {} ",
      numIdPresupuesto);
    CollectionModelPolizaIndividualCertificadoResource result = individualesApiClient
      .findAllPolizaUsingGET(polizaUtils.getOrSetUUID(null), getMapParamQuery(numIdPresupuesto), PageRequest.of(0, 1)).getBody();

    if (result == null || result.getEmbedded().getPolizasIndividuales().isEmpty()) {
      log.debug("No se encuentra la poliza individual");
    } else {
      resultBool = true;
    }
    return resultBool;
  }


  /**
   * Comprueba si existe una poliza individual en historico
   * con ese numIdPresupuesto
   *
   * @param numIdPresupuesto id equivalente a idPresupuestoOrigen
   * @return listado de fotos del historico o null si no existe la poliza
   */
  @Override
  public boolean getHistoricoIndividual(String numIdPresupuesto) {

    boolean resultBool = false;

    log.debug("Extrayendo historico con numIdPresupuesto: {}", numIdPresupuesto);

    CollectionModelHistoricoPolizaIndividualCertificadoResource result = historicoIndividualesApiClient
      .findAllHistoricoPolizaUsingGET(polizaUtils.getOrSetUUID(null),
        getMapParamQuery(numIdPresupuesto),
        PageRequest.of(0, 1))
      .getBody();

    if (result == null || result.getEmbedded().getPolizasIndividuales().isEmpty()) {
      log.debug("No se encuentra la poliza individual");
    } else {
      resultBool = true;
    }
    return resultBool;
}

  private Map<String, List<String>> getMapParamQuery(String numIdPresupuesto) {
    Map<String, List<String>> mapParams = new HashMap<>(DEFAULT_INITIAL_CAPACITY);

    if (StringUtils.isNotBlank(numIdPresupuesto)) {
      mapParams.put("datosIdentificativos.numIdPresupuesto", List.of(numIdPresupuesto));
    }
    return mapParams;
  }
}
