package com.santalucia.cdc.core.domain.budgets.common.payment.method;

import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
