package com.santalucia.cdc.core.domain.budgets.common.payment;


import com.santalucia.cdc.core.domain.TipoMDLDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoCobroDomain {
    private TipoMDLDomain nivelCobro;
    private TipoMDLDomain formaPago;
    private TipoMDLDomain medioCobroPago;
    private DatoMedioCobroDomain datoMedioCobro;
    private List<DatoOtrosCobPagBancDomain> datosOtrosCobPagBanc;
}
