package com.santalucia.cdc.core.domain.budgets.common.figure;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FiguraDomain {
    private String numAsegurados;
    private List<DatoPersonalDomain> datosPersonales;
}
