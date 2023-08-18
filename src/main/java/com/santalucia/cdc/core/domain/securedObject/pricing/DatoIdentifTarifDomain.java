package com.santalucia.cdc.core.domain.securedObject.pricing;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.cdc.core.domain.securedObject.TipoComplementoDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoIdentifTarifDomain {
    private TipoComplementoDomain tipoComplemento;
    private TipoMDLDomain categoria;
    private TipoMDLDomain agrupGarantia;
    private String numOrdenAgrupGar;
    private String agrupGarantiaHog;
    private TipoMDLDomain garantiaUnitaria;
    private String numOrdenPersonaRol;
    private TipoMDLDomain riesgo;
    private String edadTarificacion;
    private String codAplicacion;
}
