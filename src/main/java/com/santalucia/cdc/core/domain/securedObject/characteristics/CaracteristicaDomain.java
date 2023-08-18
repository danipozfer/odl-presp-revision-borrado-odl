package com.santalucia.cdc.core.domain.securedObject.characteristics;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class CaracteristicaDomain {
    private List<DomicilioDomain> domicilios;
    private List<FiguraDomain> figuras;
    private List<AnimalDomain> animales;
    private List<DispositivoElectrDomain> dispositivosElectr;
}
