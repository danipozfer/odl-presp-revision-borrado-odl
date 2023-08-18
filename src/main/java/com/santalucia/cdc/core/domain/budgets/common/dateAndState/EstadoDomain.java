package com.santalucia.cdc.core.domain.budgets.common.dateAndState;

import com.santalucia.cdc.core.domain.budgets.common.HistoricoDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class EstadoDomain {
    private List<HistoricoDomain> historicos;
}
