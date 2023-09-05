package com.santalucia.cdc.core.domain.securedObject.pricing;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FechaDomain {
    private Instant fecEfectoComplemento;
    private Instant fecTarificacion;
    private Instant fecEfectoAgrGarantia;
    private Instant fecExtincion;
}
