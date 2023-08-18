package com.santalucia.cdc.core.domain;


import com.santalucia.cdc.core.domain.budgets.collectiveBudget.PresupuestoColectivoDomain;

import com.santalucia.cdc.core.domain.declaration.DeclaracionDomain;
import com.santalucia.cdc.core.domain.securedObject.ObjetosAseguradosDomain;
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
@SuppressWarnings("NullAway.Init")
public class EventoPresupuestoColDomain {
    private String indTipoEvento;
    private PresupuestoColectivoDomain presupuestoColectivo;
    private List<ObjetosAseguradosDomain> objetosAsegurados;
    private List<DeclaracionDomain> declaracion;
}
