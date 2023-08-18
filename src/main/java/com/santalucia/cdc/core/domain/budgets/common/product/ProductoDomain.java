package com.santalucia.cdc.core.domain.budgets.common.product;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class ProductoDomain {
    private TipoMDLDomain ramoComercial;
    private TipoMDLDomain modalidadComercial;
    private TipoMDLDomain productoTecnico;
    private TipoMDLDomain productoComercial;
    private String desModalidadInterna;
}
