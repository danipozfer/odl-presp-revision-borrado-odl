package com.santalucia.cdc.core.service.policies;

public interface PolizaColectivaClientService {

  boolean getPolizaColectiva(String numIdPresupuesto);

  boolean getHistoricoColectiva(String numIdPresupuesto);
}
