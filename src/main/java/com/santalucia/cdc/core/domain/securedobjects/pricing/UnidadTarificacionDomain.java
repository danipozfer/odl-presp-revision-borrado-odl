package com.santalucia.cdc.core.domain.securedobjects.pricing;


import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedobjects.pricing.com.DatosPropiosDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadTarificacionDomain {
    private String numIdNivTarificacion;
    private TipoMDLDomain tipoPrima;
    private TipoMDLDomain moneda;
    private TipoMDLDomain nivelTarificacion;
    private DatoIdentifTarifDomain datoIdentifTarif;
    private FechaDomain fecha;
    private DatosPropiosDomain datosPropios;
    private PrimaDomain prima;
    private CapitalDomain capital;
    private List<SobreprimaDomain> sobreprimas;
    private List<BonificacionDescDomain> bonificacionesDesc;
    private List<ComposicionGarantiaDomain> composicionesGarantias;
}
