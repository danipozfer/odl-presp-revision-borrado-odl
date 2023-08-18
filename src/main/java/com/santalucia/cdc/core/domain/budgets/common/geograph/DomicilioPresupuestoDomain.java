package com.santalucia.cdc.core.domain.budgets.common.geograph;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DomicilioPresupuestoDomain {
    private String idDomicilio;
    private TipoMDLDomain pais;
    private TipoMDLDomain localidad;
    private TipoMDLDomain provincia;
    private String codMunicipio;
    private String codEntColectiva;
    private String codEntSingular;
    private String codNucPobla;
    private TipoMDLDomain denomPobla;
    private String codPostal;
    private TipoMDLDomain tipoVia;
    private String desDomicilio;
    private String numNumero;
    private String numComplemento;
    private String numBloque;
    private String numPortal;
    private String numEscalera;
    private String numPiso;
    private String numPuerta;
    private String blNormalizado;
    private String desOtrosDatos;
}
