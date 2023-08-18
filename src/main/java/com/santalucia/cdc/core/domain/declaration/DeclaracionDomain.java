package com.santalucia.cdc.core.domain.declaration;

import com.santalucia.cdc.core.domain.MetadataDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DeclaracionDomain {
    private String id;
    private DatoIdentificativoDomain datoIdentificativo;
    private List<CaracteristicaDomain> caracteristicas;
    private MetadataDomain metadata;
}
