package com.santalucia.cdc.core.domain.budgets.common.dateAndState;

import com.santalucia.cdc.core.domain.budgets.common.dateAndState.hist.HistoricoDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstadoDomain {
  private List<HistoricoDomain> historicos;
}
