package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PresupuestoColectivoClientService {


  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoColectivoDomain getCollectiveBudget(String indAnonimizacion, String indFormalizado);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoColectivoResource findApiSnapshotCollectiveBudget(String indAnonimizacion, String indFormalizado);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoColectivoDomain updateCollectiveBudget(PresupuestoColectivoDomain collectiveBudget, String collectiveBudgetId, UUID uuid);


}
