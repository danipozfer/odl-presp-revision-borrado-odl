package com.santalucia.cdc.core.domain.securedobjects.pricing;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrimaDomain {
    private double impPrimaTarifa;
    private double impPrimaBruta;
    private double impPrimaNoConsumida;
    private double impPrimaNatural;
    private double impPrimaNivelada;
}
