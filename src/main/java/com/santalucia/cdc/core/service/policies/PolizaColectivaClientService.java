package com.santalucia.cdc.core.service.policies;

import com.santalucia.cdc.core.domain.policy.polizas.PolizaDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface PolizaColectivaClientService {

  boolean getPolizaColectiva(String numIdPresupuesto);

  boolean getHistoricoColectiva(String numIdPresupuesto);
}
