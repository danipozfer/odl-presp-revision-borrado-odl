package com.santalucia.cdc.core.domain.securedObject.pricing;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedObject.ComposicionCaractDomain;
import com.santalucia.cdc.core.domain.securedObject.ComposicionServicioDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class ComposicionGarantiaDomain {
    private TipoMDLDomain garantia;
    private String indOrigenRecomendador;
    private List<TipoMDLDomain> composicionesSubgarant;
    private List<ComposicionServicioDomain> composicionesServicios;
    private List<ComposicionCaractDomain> composicionesCaract;
}
