package com.santalucia.cdc.core.domain.budgets.individualBudget.amount.level;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NivelPresupuestoDomain {
    private TipoMDLDomain tipoPrima;
    private String indModServEconomico;
    private PrimaDomain prima;
    private List<SobreprimaDomain> sobreprimas;
    private List<BonificacionDescDomain> bonificacionesDesc;
    private List<ImpuestoDomain> impuestos;
}
