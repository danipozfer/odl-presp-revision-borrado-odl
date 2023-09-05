package com.santalucia.cdc.core.domain.budgets.common.geograph;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstructuraGeograficaDomain {
    private DomicilioPresupuestoDomain domicilioPresupuesto;
    private CoordenadaDomain coordenada;
}
