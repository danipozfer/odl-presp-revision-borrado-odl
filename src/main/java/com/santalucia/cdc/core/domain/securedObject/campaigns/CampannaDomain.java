package com.santalucia.cdc.core.domain.securedObject.campaigns;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class CampannaDomain {
    private String codAplicacion;
    private String idCampanna;
    private TipoMDLDomain campannaComercial;
    private TipoMDLDomain tipoCampanna;
    private TipoMDLDomain incentivo;
    private String indCaracIncentivo;
    private Instant fecInicio;
    private Instant fecFinVigencia;
}
