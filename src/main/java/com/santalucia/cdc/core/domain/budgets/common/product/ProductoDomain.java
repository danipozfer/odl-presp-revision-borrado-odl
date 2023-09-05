package com.santalucia.cdc.core.domain.budgets.common.product;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDomain {
    private TipoMDLDomain ramoComercial;
    private TipoMDLDomain modalidadComercial;
    private TipoMDLDomain productoTecnico;
    private TipoMDLDomain productoComercial;
    private String desModalidadInterna;
}
