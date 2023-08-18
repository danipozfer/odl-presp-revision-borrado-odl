package com.santalucia.cdc.core.domain.budgets.individualBudget.amount;

import com.santalucia.cdc.core.domain.TipoMDLDomain;
import com.santalucia.contratacion.presupuestos_odl.carga.avro.presupuestoIndividual.NivelPresupuesto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@SuppressWarnings("NullAway.Init")
public class ImporteDomain {
    private TipoMDLDomain moneda;
    private double impTotalRecibo;
    private List<NivelPresupuesto> nivelesPresupuestos;
}
