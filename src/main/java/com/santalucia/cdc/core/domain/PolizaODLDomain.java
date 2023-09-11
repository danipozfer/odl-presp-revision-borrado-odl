package com.santalucia.cdc.core.domain;

import com.santalucia.cdc.core.domain.policy.movimientos.ColMovimientoDomain;
import com.santalucia.cdc.core.domain.policy.objeto.asegurado.ColObjetoAseguradoDomain;
import com.santalucia.cdc.core.domain.policy.polizas.PolizaDomain;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto poliza
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PolizaODLDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	private PolizaDomain polizaIndividual = new PolizaDomain();
	private List<ColObjetoAseguradoDomain> objetoAsegurado = new ArrayList<>(DEFAULT_CAPACITY);
	private ColMovimientoDomain movimientos = new ColMovimientoDomain();

}
