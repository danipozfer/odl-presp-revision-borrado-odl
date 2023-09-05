package com.santalucia.cdc.core.domain.budgets.common.payment.method;


import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatoCobroFisicoDomain {
    private TipoMDLDomain tipoDomCobro;
    private TipoMDLDomain pais;
    private TipoMDLDomain provincia;
    private TipoMDLDomain localidad;
    private String codMunicipio;
    private String codPostal;
    private String codEntColectiva;
    private String codEntSingular;
    private String codNucPobla;
    private TipoMDLDomain denomPoblaPers;
    private TipoMDLDomain tipoVia;
    private String descDomicilio;
    private String numDomicilio;
    private String compNumDomic;
    private String numBloqueDomic;
    private String numPortalDomic;
    private String numEscalDomic;
    private String numPisoDomic;
    private String numPuertaDomic;
    private String otrosDatosDomic;
}
