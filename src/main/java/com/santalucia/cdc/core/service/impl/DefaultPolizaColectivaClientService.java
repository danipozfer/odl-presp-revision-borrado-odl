package com.santalucia.cdc.core.service.impl;

import com.santalucia.cdc.core.mappers.insurance.HistPolizaColectivaDomainMapper;
import com.santalucia.cdc.core.mappers.insurance.PolizaColectivaDomainMapper;
import com.santalucia.cdc.core.mappers.insurance.PolizaIndividualDomainMapper;
import com.santalucia.cdc.core.mappers.insurance.HistPolizaIndividualDomainMapper;
import com.santalucia.cdc.core.service.PolizaColectivaClientService;
import com.santalucia.cdc.core.service.PolizaUtilsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
