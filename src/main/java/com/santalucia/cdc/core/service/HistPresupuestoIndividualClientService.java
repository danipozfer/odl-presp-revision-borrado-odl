package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface HistPresupuestoIndividualClientService {
  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoIndividualDomain updateHistIndividualBudget(PresupuestoIndividualDomain individualBudget, String individualBudgetId);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  List<PresupuestoIndividualDomain> findAllHistoricIndividualBudget(String indAnonimizacion, String indFormalizado);
}
