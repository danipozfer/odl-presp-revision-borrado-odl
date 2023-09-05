package com.santalucia.cdc.core.domain.securedObject.pricing.com;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrevisionDomain {
    private String numAnnoSeguro;
    private double impCapital;
}
