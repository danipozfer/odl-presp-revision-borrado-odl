package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;

import java.util.List;
import java.util.UUID;

public interface HistObjetosAseguradosClientService {
  List<ObjetosAseguradosDomain> findAllHistoricSecuredObject(String idPresupuestoODL);

  ObjetosAseguradosDomain updateHistSecuredObject(ObjetosAseguradosDomain securedObject, String securedObjectId, UUID uuid);
}
