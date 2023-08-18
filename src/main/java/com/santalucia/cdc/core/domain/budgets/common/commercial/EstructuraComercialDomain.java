package com.santalucia.cdc.core.domain.budgets.common.commercial;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class EstructuraComercialDomain {
    private TipoMDLDomain canalMediacion;
    private TipoMDLDomain medioDistribucion;
    private TipoMDLDomain agencia;
    private TipoMDLDomain zona;
    private TipoMDLDomain distrito;
    private TipoMDLDomain oficina;
    private String codMediador;
    private TipoMDLDomain tipoMediador;
    private TipoMDLDomain tipoColaborador;
    private String codColaborador;
    private TipoMDLDomain tipoColaboradorIni;
    private String codColabInicial;
    private String codMonitor;
    private String codMonitorInicial;
    private TipoMDLDomain redVenta;
    private String indTipoMedioCom;
}
