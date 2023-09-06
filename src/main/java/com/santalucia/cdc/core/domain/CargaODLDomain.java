package com.santalucia.cdc.core.domain;

import com.santalucia.cdc.core.domain.budgets.collectivebudget.PresupuestoColectivoDomain;
import com.santalucia.cdc.core.domain.budgets.individualbudget.PresupuestoIndividualDomain;
import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedobjects.ObjetosAseguradosDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Objeto presupuesto
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CargaODLDomain {
    private String indTipoEvento;
    private PresupuestoIndividualDomain presupuestoIndividual;
    private PresupuestoColectivoDomain presupuestoColectivo;
    private List<ObjetosAseguradosDomain> objetosAsegurados;
    private List<DeclaracionDomain> declaracion;
}
