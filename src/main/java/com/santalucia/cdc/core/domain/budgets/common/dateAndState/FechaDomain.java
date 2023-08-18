package com.santalucia.cdc.core.domain.budgets.common.dateAndState;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class FechaDomain {
  private Instant fecAlta;
  private Instant fecEfectoPoliza;
  private Instant fecFinValidez;
  private Instant fecTarificacion;
  private Instant fecVencPoliza;
  private Instant fechaAnonimizacion;
}
