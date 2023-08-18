package com.santalucia.cdc.core.domain.budgets.common;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
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
    private String blNormalizado;
    private String desOtrosDatos;
}
