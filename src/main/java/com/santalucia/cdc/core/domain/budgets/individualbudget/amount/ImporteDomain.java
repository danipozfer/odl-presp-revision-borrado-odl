package com.santalucia.cdc.core.domain.budgets.individualbudget.amount;

import com.santalucia.cdc.core.domain.budgets.individualbudget.amount.level.NivelPresupuestoDomain;
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
public class ImporteDomain {
    private TipoMDLDomain moneda;
    private double impTotalRecibo;
    private List<NivelPresupuestoDomain> nivelesPresupuestos;
}
