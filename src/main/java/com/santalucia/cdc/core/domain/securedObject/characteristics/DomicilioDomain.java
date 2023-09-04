package com.santalucia.cdc.core.domain.securedObject.characteristics;


import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DomicilioDomain {
    private String idDomicilio;
    private TipoMDLDomain pais;
    private TipoMDLDomain provincia;
    private TipoMDLDomain localidad;
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
