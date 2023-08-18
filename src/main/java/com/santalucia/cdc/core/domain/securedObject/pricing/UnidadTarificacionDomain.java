package com.santalucia.cdc.core.domain.securedObject.pricing;


import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedObject.DatosPropiosDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
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
