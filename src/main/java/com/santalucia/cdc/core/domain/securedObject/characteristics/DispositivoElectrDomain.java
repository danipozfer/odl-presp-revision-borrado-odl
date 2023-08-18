package com.santalucia.cdc.core.domain.securedObject.characteristics;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DispositivoElectrDomain {
    private TipoMDLDomain marcaDispElectr;
    private TipoMDLDomain modeloDispElectr;
    private String codIdentificador;
    private double impCoste;
}
