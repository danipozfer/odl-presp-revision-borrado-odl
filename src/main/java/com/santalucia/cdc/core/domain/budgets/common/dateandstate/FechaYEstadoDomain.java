package com.santalucia.cdc.core.domain.budgets.common.dateandstate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FechaYEstadoDomain {
    private FechaDomain fecha;
    private EstadoDomain estado;
}
