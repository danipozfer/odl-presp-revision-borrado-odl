package com.santalucia.cdc.core.domain.declaration.com;


import com.santalucia.cdc.core.domain.declaration.com.resp.RespuestaDomain;
import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
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
    private TipoMDLDomain pregunta;
    private String indAplicPregunta;
    private List<RespuestaDomain> respuestas;
}
