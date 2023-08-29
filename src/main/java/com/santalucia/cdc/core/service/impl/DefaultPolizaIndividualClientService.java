package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.mappers.insurance.HistPolizaIndividualDomainMapper;
import com.santalucia.cdc.core.mappers.insurance.PolizaIndividualDomainMapper;
import com.santalucia.cdc.core.service.PolizaIndividualClientService;
import com.santalucia.cdc.core.service.PolizaUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
