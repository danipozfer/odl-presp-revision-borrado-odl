package com.santalucia.cdc.core.domain.securedObject.pricing;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class FechaDomain {
    private Instant fecEfectoComplemento;
    private Instant fecTarificacion;
    private Instant fecEfectoAgrGarantia;
    private Instant fecExtincion;
}
