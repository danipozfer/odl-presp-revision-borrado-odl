package com.santalucia.cdc.core.service.policies;


import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

public interface PolizaIndividualClientService {
  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  boolean getPolizaIndividual(String numIdPresupuesto);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  boolean getHistoricoIndividual(String numIdPresupuesto);
}
