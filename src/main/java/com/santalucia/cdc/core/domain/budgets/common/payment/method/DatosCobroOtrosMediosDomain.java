package com.santalucia.cdc.core.domain.budgets.common.payment.method;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosCobroOtrosMediosDomain {
  String codOrigen;
  String numValor;
  String descOrigen;
}
