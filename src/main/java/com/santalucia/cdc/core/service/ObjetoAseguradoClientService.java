package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface ObjetoAseguradoClientService {

  List<ObjetosAseguradosDomain> findAllHistoricSecuredObject(String idPresupuestoODL);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  List findObjetoAseguradoByIdPres(String idPresupuestoODL);

  ObjetosAseguradosDomain updateSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId, UUID uuid);

  ObjetosAseguradosDomain updateHistSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId, UUID uuid);
}
