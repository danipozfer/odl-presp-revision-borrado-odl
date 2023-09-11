package com.santalucia.cdc.core.service.policies;

import com.santalucia.cdc.core.domain.policy.polizas.PolizaDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface PolizaIndividualClientService {
  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PolizaDomain getPolizaIndividual(String numIdPresupuesto, UUID uuid);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  String findApiIdUltimaFotoIndividual(String numIdPresupuesto);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  List<PolizaDomain> findAllHistoricoIndividual(String numIdPresupuesto, UUID uuid);
}
