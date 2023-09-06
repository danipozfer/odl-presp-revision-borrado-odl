package com.santalucia.cdc.core.domain.securedobjects.characteristics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CaracteristicaDomain {
    private List<DomicilioDomain> domicilios;
    private List<FiguraDomain> figuras;
    private List<AnimalDomain> animales;
    private List<DispositivoElectrDomain> dispositivosElectr;
}
