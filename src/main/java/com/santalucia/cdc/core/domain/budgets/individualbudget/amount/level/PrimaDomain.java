package com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PrimaDomain {
    private double impPrimaTarifa;
    private double impTotalPoliza;
    private double impPrimaPura;
    private double impPrimaInventario;
    private double impPrimaCompetencia;
}
