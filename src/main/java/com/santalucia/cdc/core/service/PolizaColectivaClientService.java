package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.insurance.polizas.PolizaDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface PolizaColectivaClientService {
  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PolizaDomain getPolizaColectiva(String numIdPresupuesto, UUID uuid);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  String findApiIdUltimaFotoColectiva(String numIdPresupuesto);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  List<PolizaDomain> findAllHistoricoColectiva(String numIdPresupuesto, UUID uuid);
}
