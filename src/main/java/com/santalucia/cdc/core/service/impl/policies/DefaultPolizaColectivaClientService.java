package com.santalucia.cdc.core.service.impl.policies;

import com.santalucia.arq.ams.odl.historico.polizas.colectivas.api.client.HistoricoPolizasColectivasApiClient;
import com.santalucia.arq.ams.odl.historico.polizas.colectivas.api.model.PagedModelEntityModelPolizasColectivasHistR2Resource;
import com.santalucia.arq.ams.odl.polizas.colectivas.api.client.PolizasColectivasApiClient;
import com.santalucia.arq.ams.odl.polizas.colectivas.api.model.PagedModelEntityModelPolizasColectivasR2Resource;
import com.santalucia.cdc.core.service.policies.PolizaColectivaClientService;
import com.santalucia.cdc.core.service.policies.PolizaUtilsService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Cliente general para las distintas APIs CRUD de polizas
 *
 * @author Nfq
 */
@Slf4j
@Service
@AllArgsConstructor
@SuppressWarnings("NullAway")
public class DefaultPolizaColectivaClientService implements PolizaColectivaClientService {

  private final PolizasColectivasApiClient colectivasApiClient;
  private final HistoricoPolizasColectivasApiClient historicoColectivasApiClient;
  private final PolizaUtilsService polizaUtils;

  private static final int DEFAULT_INITIAL_CAPACITY = 1 << 2; // aka 4


  /**
   * Comprueba si existe una poliza colectiva en ultima foto con
   * ese numIdPresupuesto
   *
   * @param numIdPresupuesto equivalente a idPresupuestoOrigen
   * @return boolean
   */
  @Override
  public boolean getPolizaColectiva(String numIdPresupuesto) {
    boolean resultBool = false;
    log.debug("La poliza colectiva, se busca con numIdPresupuesto {} ",
      numIdPresupuesto);
    UUID uuid = polizaUtils.getOrSetUUID(null);

    PagedModelEntityModelPolizasColectivasR2Resource result = colectivasApiClient
      .findAllPolizaColectivaUsingGET(uuid, Optional.of(getMapParamQuery(numIdPresupuesto)), PageRequest.of(0, 1)).getBody();

    if (result == null || result.getEmbedded().getPolizasColectivas().isEmpty()) {
      log.debug("No se encuentra la poliza colectiva");
    } else {
      resultBool = true;
    }
    return resultBool;
  }

  /**
   * Obtiene todas las polizas del historico en base a numero de poliza y
   * certificado si es necesario
   *
   * @param numIdPresupuesto equivalente a idPresupuestoOrigen
   * @return listado de fotos del historico o null si no existe la poliza
   */
  @Override
  public boolean getHistoricoColectiva(String numIdPresupuesto) {
    boolean resultBool = false;
    PagedModelEntityModelPolizasColectivasHistR2Resource result = historicoColectivasApiClient
      .findAllPolizaColectivaUsingGET(polizaUtils.getOrSetUUID(null), Optional.of(getMapParamQuery(numIdPresupuesto)),
        PageRequest.of(0, 1))
      .getBody();

    if (result == null || result.getEmbedded().getPolizasColectivas().isEmpty()) {
      log.debug("No se encuentra la poliza colectiva");
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
