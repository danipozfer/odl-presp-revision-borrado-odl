package com.santalucia.cdc.core.domain.declaration;


import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class CaracteristicaDomain {
    private TipoMDLDomain pregunta;
    private String indAplicPregunta;
    private List<RespuestaDomain> respuestas;
}
