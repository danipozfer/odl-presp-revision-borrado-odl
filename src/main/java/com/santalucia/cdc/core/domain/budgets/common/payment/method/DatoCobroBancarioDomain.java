package com.santalucia.cdc.core.domain.budgets.common.payment.method;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoCobroBancarioDomain {
    private TipoMDLDomain tipoDomBancario;
    private TipoMDLDomain entidadBancaria;
    private String numDigContrNumCuent;
    private String numDigContrEntidOfic;
    private String numCuentBanc;
    private String titulCuentBanc;
    private String codIban;
    private String codBic;
}
