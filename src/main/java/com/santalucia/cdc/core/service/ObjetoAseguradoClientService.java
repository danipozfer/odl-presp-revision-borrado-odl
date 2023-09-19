package com.santalucia.cdc.core.service;

import com.santalucia.arq.ams.odl.presupuestos.objeto.asegurado.api.model.EntityModelObjetoAseguradoPresupuestoResource;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;

import java.util.List;
import java.util.UUID;

public interface ObjetoAseguradoClientService {


  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  ObjetosAseguradosDomain getSecuredObject(String idObjetoAseguradoODL);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  EntityModelObjetoAseguradoPresupuestoResource findApiSnapshotSecuredObject(String idObjetoAseguradoODL);

  @Retryable(maxAttemptsExpression = "${app.custom.features.retryMaxAttempt}", backoff = @Backoff(delayExpression = "${app.custom.features.retryInterval}"))
  ObjetosAseguradosDomain updateSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId);

}
