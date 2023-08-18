package com.santalucia.cdc.core.domain.budgets.common.payment;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoOtrosCobPagBancDomain {
    private TipoMDLDomain tipoDomBancOtroCob;
    private TipoMDLDomain entidadBancOtroCob;
    private String numDigContrNumCuent;
    private String numDigContrEntidOfic;
    private String numCuentaBanc;
    private String titulCuentBanc;
    private String codIban;
    private String codBic;
}
