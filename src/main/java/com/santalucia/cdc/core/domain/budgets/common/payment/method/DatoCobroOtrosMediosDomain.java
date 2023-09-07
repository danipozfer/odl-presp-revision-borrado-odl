package com.santalucia.cdc.core.domain.budgets.common.payment.method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatoCobroOtrosMediosDomain {
  String codOrigen;
  String numValor;
  String descOrigen;
}
