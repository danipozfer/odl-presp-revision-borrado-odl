package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;

import java.util.List;
import java.util.UUID;

public interface PolizaIndividualClientService {
    PolizaDomain getPolizaIndividual(String numIdPresupuesto, UUID uuid);

  String findApiIdUltimaFotoIndividual(String numIdPresupuesto);

  List<PolizaDomain> findAllHistoricoIndividual(String numIdPresupuesto, UUID uuid);
}
