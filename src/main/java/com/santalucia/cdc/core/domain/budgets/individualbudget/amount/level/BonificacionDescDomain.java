package com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BonificacionDescDomain {
    private TipoMDLDomain bonificacionDesc;
    private double porCesionComisionMed;
    private double impBonificacionDesc;
}
