package com.santalucia.cdc.core.domain.securedObject;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class ComposicionServicioDomain {
    private String idServicio;
    private TipoMDLDomain subgarantia;
    private double impCosteServicio;
    private String codObligatoriedadSerOrigen;
    private String codObligatoriedadSerMDL;
    private String descObligatoriedadSerOrigen;
}
