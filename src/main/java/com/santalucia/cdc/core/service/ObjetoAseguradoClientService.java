package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface ObjetoAseguradoClientService {

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public void insertSecuredObject(ObjetosAseguradosDomain objetosAsegurados);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public ObjetosAseguradosDomain updateSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId, UUID uuid);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public ObjetosAseguradosDomain getSecuredObject(String idObjetoAseguradoODL);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public EntityModelObjetoAseguradoResource findApiSnapshotSecuredObject(String idObjetoAseguradoODL);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public ObjetosAseguradosDomain insertHistoricSecuredObject(ObjetosAseguradosDomain securedObject);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public List<ObjetosAseguradosDomain> findAllHistoricSecuredObject(String idObjetoAseguradoODL, String numVersObjetoAsegurado);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  public void rollbackSecuredObject(String idObjetoAseguradoODL, String numVersMovObjetoAseguradoODL, UUID uuid);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  String findApiIdHistoricSecuredObject(String idObjetoAseguradoODL, String numVersObjetoAseguradoODL);
}
