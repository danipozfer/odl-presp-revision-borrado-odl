package com.santalucia.cdc.core.service.policies;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface PolizaColectivaClientService {
  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  boolean getPolizaColectiva(String numIdPresupuesto);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  boolean getHistoricoColectiva(String numIdPresupuesto);
}
