package com.santalucia.cdc.core.domain.securedObject.pricing;

import com.santalucia.cdc.core.domain.securedObject.PrevisionDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class CapitalDomain {
    private double impCapital;
    private double impCapitalReducido;
    private double impCapitalBasico;
    private double impCapitalNivelado;
    private double impCapitalConsolidado;
    private double impCapitalNatural;
    private double impTramitacionSiniestros;
    private double impTrasladoMut;
    private double porIncrementoCapital;
    private List<PrevisionDomain> previsiones;
}
