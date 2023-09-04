package com.santalucia.cdc.core.domain.securedObject.pricing;

import com.santalucia.cdc.core.domain.securedObject.pricing.com.PrevisionDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
