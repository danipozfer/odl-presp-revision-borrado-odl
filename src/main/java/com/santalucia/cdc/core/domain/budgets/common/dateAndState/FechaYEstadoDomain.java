package com.santalucia.cdc.core.domain.budgets.common.dateAndState;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class FechaYEstadoDomain {
    private FechaDomain fecha;
    private EstadoDomain estado;

}
