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
public class DispositivoElectrDomain {
    private TipoMDLDomain marcaDispElectr;
    private TipoMDLDomain modeloDispElectr;
    private String codIdentificador;
    private double impCoste;
}
