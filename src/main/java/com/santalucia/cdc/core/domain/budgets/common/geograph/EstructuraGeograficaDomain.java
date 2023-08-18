package com.santalucia.cdc.core.domain.budgets.common.geograph;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class EstructuraGeograficaDomain {
    private DomicilioPresupuestoDomain domicilioPresupuesto;
    private CoordenadaDomain coordenada;
}
