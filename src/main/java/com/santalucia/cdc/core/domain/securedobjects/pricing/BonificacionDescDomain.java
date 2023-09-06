package com.santalucia.cdc.core.domain.securedobjects.pricing;

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
    private TipoMDLDomain bonDesNvlPoliza;
    private double impBonificacion;
    private double porCesionComisionMed;
    private double impTasaBonificacion;
    private String indCalculoBonific;
}
