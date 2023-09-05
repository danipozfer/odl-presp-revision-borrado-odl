package com.santalucia.cdc.core.domain.budgets.common.geograph;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoordenadaDomain {
    private TipoMDLDomain tipoCoordenada;
    private double numCoordX;
    private double numCoordY;
    private String indSistema;
}
