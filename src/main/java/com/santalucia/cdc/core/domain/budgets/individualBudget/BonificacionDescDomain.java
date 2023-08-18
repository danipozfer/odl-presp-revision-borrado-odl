package com.santalucia.cdc.core.domain.budgets.individualBudget;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class BonificacionDescDomain {
    private TipoMDLDomain bonificacionDesc;
    private double porCesionComisionMed;
    private double impBonificacionDesc;
}
