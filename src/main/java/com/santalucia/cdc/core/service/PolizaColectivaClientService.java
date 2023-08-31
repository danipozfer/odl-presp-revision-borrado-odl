package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;

import java.util.List;
import java.util.UUID;

public interface PolizaColectivaClientService {
    PolizaDomain getPolizaColectiva(String numIdPresupuesto, UUID uuid);

  String findApiIdUltimaFotoColectiva(String numIdPresupuesto);

  List<PolizaDomain> findAllHistoricoColectiva(String numIdPresupuesto, UUID uuid);
}
