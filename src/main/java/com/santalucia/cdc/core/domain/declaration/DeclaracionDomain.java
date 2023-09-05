package com.santalucia.cdc.core.domain.declaration;

import com.santalucia.cdc.core.domain.MetadataDomain;
import com.santalucia.cdc.core.domain.declaration.com.CaracteristicaDomain;
import com.santalucia.cdc.core.domain.declaration.com.DatoIdentificativoDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeclaracionDomain {
    private String id;
    private DatoIdentificativoDomain datoIdentificativo;
    private List<CaracteristicaDomain> caracteristicas;
    private MetadataDomain metadata;
}
