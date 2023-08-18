package com.santalucia.cdc.core.domain.budgets.individualBudget;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class NivelPresupuestoDomain {
    private TipoMDLDomain tipoPrima;
    private String indModServEconomico;
    private PrimaDomain prima;
    private List<SobreprimaDomain> sobreprimas;
    private List<BonificacionDescDomain> bonificacionesDesc;
    private List<ImpuestoDomain> impuestos;
}
