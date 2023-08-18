package com.santalucia.cdc.core.domain.budgets.common.geograph;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class CoordenadaDomain {
    private TipoMDLDomain tipoCoordenada;
    private double numCoordX;
    private double numCoordY;
    private String indSistema;
}
