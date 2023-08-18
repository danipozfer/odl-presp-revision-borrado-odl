package com.santalucia.cdc.core.domain.budgets.common.figure;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class FiguraDomain {
    private String numAsegurados;
    private List<DatoPersonalDomain> datosPersonales;
}
