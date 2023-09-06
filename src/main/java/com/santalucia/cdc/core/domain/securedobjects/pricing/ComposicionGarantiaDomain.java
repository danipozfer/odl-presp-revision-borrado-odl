package com.santalucia.cdc.core.domain.securedobjects.pricing;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionCaractDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.ComposicionServicioDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposicionGarantiaDomain {
    private TipoMDLDomain garantia;
    private String indOrigenRecomendador;
    private List<TipoMDLDomain> composicionesSubgarant;
    private List<ComposicionServicioDomain> composicionesServicios;
    private List<ComposicionCaractDomain> composicionesCaract;
}
