package com.santalucia.cdc.core.domain.budgets.common.payment;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
