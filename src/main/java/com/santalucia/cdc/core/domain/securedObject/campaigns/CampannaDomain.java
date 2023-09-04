package com.santalucia.cdc.core.domain.securedObject.campaigns;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
