package com.santalucia.cdc.core.domain.securedObject.pricing;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class PrimaDomain {
    private double impPrimaTarifa;
    private double impPrimaBruta;
    private double impPrimaNoConsumida;
    private double impPrimaNatural;
    private double impPrimaNivelada;
}
