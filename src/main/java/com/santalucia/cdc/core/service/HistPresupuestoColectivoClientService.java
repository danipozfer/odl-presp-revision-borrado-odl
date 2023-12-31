package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface HistPresupuestoColectivoClientService {

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoColectivoDomain updateHistCollectiveBudget(PresupuestoColectivoDomain collectiveBudget, String collectiveBudgetId);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  List<PresupuestoColectivoDomain> findAllHistoricCollectiveBudget(String indAnonimizacion, String indFormalizado);
}
