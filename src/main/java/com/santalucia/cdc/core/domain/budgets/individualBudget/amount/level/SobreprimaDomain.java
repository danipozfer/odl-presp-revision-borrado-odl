package com.santalucia.cdc.core.domain.budgets.individualBudget.amount.level;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SobreprimaDomain {
    private TipoMDLDomain sobreprima;
    private double impSobreprima;
}
