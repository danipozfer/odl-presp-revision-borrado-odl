package com.santalucia.cdc.core.domain.budgets.common.payment;


import com.santalucia.cdc.core.domain.declaration.com.resp.type.TipoMDLDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatoCobroDomain {
    private TipoMDLDomain nivelCobro;
    private TipoMDLDomain formaPago;
    private TipoMDLDomain medioCobroPago;
    private DatoMedioCobroDomain datoMedioCobro;
    private List<DatoOtrosCobPagBancDomain> datosOtrosCobPagBanc;
}
