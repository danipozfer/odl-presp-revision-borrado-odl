package com.santalucia.cdc.core.domain.policy.movimientos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ultimo Movimiento
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class UltimoMovimientoDomain {

	/** Numero de version de la poliza */
	private int versPolizaOrigen;
	/** Numero de version ODL de la poliza */
	private int versPolizaODL;
	/** Codigo del tipo de movimiento */
	private String codTipMovim;
	/** Denominacion del tipo de movimiento */
	private String descTipMovim;
	/** Codigo del estado del movimiento */
	private String codEstadMovim;
	/** Denominacion del estado del movimiento */
	private String descEstadMovim;
	/** Fecha de efecto del movimiento */
	private String fecInicMovim;
	/** Fecha de liquidacion del movimiento */
	private String fecLiqMovim;
	/** Numero de movimiento que genera la anulacion*/
	private String numMovimAnulac;
}
