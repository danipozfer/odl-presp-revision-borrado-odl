package com.santalucia.cdc.core.domain.securedObject.pricing;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class BonificacionDescDomain {
    private TipoMDLDomain bonDesNvlPoliza;
    private double impBonificacion;
    private double porCesionComisionMed;
    private double impTasaBonificacion;
    private String indCalculoBonific;
}
