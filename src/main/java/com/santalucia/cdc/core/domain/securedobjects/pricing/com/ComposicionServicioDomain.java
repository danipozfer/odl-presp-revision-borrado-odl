package com.santalucia.cdc.core.domain.securedobjects.pricing.com;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ComposicionServicioDomain {
    private String idServicio;
    private TipoMDLDomain subgarantia;
    private double impCosteServicio;
    private String codObligatoriedadSerOrigen;
    private String codObligatoriedadSerMDL;
    private String descObligatoriedadSerOrigen;
}
