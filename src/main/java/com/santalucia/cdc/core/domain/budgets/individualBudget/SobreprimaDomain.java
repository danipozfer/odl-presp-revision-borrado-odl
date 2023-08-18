package com.santalucia.cdc.core.domain.budgets.individualBudget;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class SobreprimaDomain {
    private TipoMDLDomain sobreprima;
    private double impSobreprima;
}
