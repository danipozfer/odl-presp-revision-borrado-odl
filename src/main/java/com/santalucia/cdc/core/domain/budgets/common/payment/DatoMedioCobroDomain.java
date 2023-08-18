package com.santalucia.cdc.core.domain.budgets.common.payment;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroBancarioDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroFisicoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroInternoDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class DatoMedioCobroDomain {
    private DatoCobroBancarioDomain datoCobroBancario;
    private DatoCobroFisicoDomain datoCobroFisico;
    private DatoCobroInternoDomain datoCobroInterno;
    private List<TipoMDLDomain> datosCobroOtrosMedios;
}
