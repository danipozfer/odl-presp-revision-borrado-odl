package com.santalucia.cdc.core.domain.budgets.common.figure.contactdata;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomicilioPersDomain {
    private String idDomicilio;
    private TipoMDLDomain paisDomPers;
    private TipoMDLDomain localidadDomPers;
    private TipoMDLDomain provinciaDomPers;
    private String codMunicipio;
    private String codEntColectiva;
    private String codEntSingular;
    private String codNucPobla;
    private TipoMDLDomain denomPoblaDomPers;
    private String codPostal;
    private TipoMDLDomain tipoViaDomPers;
    private String desDomicilio;
    private String numDomicilio;
    private String numComplemento;
    private String numBloque;
    private String numPortal;
    private String numEscalera;
    private String numPiso;
    private String numPuerta;
    private String indNormalizado;
    private String desOtrosDatos;
}
