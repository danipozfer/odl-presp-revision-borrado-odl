package com.santalucia.cdc.core.service;

import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;

import java.util.List;
import java.util.UUID;

public interface HistDeclaracionClientService {
  List<DeclaracionDomain> findDeclarationByIdPres(String idPresupuestoODL);

  DeclaracionDomain updateHistDeclaration(DeclaracionDomain declaracion, String declaracionId, UUID uuid);
}
