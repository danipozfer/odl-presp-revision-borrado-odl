package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PresupuestoIndividiualClientService {



  /*@Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoIndividualDomain getIndividualBudget(String indAnonimizacion, String indFormalizado);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  EntityModelPresupuestoIndividualResource findApiSnapshotIndividualBudget(String indAnonimizacion, String indFormalizado);
*/
  List<PresupuestoIndividualDomain> findIndividualBudgets(String indAnonimizacion, String indFormalizado);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  PresupuestoIndividualDomain updateIndividualBudget(PresupuestoIndividualDomain individualBudget, String individualBudgetId, UUID uuid);


}
