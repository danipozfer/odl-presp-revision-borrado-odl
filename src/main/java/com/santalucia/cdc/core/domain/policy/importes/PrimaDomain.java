package com.santalucia.cdc.core.domain.policy.importes;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Prima
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class PrimaDomain {

	/** Importe de Prima de Tarifa a Nivel de Poliza*/
	private Double impPrimaTarifa;
	/** Importe Total a Nivel de Poliza*/
	private Double impTotalPoliza;
	/** Importe de Prima Pura a Nivel de Poliza*/
	private Double impPrimaPura;
	/** Importe de Prima de Inventario a Nivel de Poliza*/
	private Double impPrimaInventario;

}
