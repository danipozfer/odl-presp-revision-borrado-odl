package com.santalucia.cdc.core.domain.budgets.common.geograph;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomicilioPresupuestoDomain {
    private String idDomicilio;
    private TipoMDLDomain paisPresup;
    private TipoMDLDomain localidadPresup;
    private TipoMDLDomain provinciaPresup;
    private String codMunicipio;
    private String codEntColectiva;
    private String codEntSingular;
    private String codNucPobla;
    private TipoMDLDomain denomPoblaPresup;
    private String codPostal;
    private TipoMDLDomain tipoViaPresup;
    private String desDomicilio;
    private String numNumero;
    private String numComplemento;
    private String numBloque;
    private String numPortal;
    private String numEscalera;
    private String numPiso;
    private String numPuerta;
    private String indNormalizado;
    private String desOtrosDatos;
}
