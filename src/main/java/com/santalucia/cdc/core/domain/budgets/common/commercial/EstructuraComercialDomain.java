package com.santalucia.cdc.core.domain.budgets.common.commercial;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
