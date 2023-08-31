package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;

public interface DeclaracionClientService {


    List<DeclaracionDomain> findHistoricDeclarationByIdres(String idPresupuestoODL);

    @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  List findDeclarationByIdPres(String idPresupuestoODL);
}
