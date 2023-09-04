package com.santalucia.cdc.core.domain.budgets.common.payment;

import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroBancarioDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroFisicoDomain;
import com.santalucia.cdc.core.domain.budgets.common.payment.method.DatoCobroInternoDomain;
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
public class DatoMedioCobroDomain {
    private DatoCobroBancarioDomain datoCobroBancario;
    private DatoCobroFisicoDomain datoCobroFisico;
    private DatoCobroInternoDomain datoCobroInterno;
    private List<TipoMDLDomain> datosCobroOtrosMedios;
}
