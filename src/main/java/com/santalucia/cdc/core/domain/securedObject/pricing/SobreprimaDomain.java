package com.santalucia.cdc.core.domain.securedObject.pricing;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class SobreprimaDomain {
    private TipoMDLDomain sobrepNvlPoliza;
    private double impSobreprima;
    private double impTasaSobreprima;
    private String indCalculoSobreprima;
}
