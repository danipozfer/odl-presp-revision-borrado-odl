package com.santalucia.cdc.core.domain.declaration;


import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class RespuestaDomain {
    private TipoMDLDomain respFacilitada;
    private String indTipoRespuesta;
}
