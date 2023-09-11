package com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PrimasTarif
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class PrimasTarifDomain {

	/** Importe de Prima de Tarifa a Nivel de tarificacion */
	private Double impPrimaTarifa;
	/** Importe de Prima de tarifa Bruta a Nivel de tarificacion*/
	private Double impPrimaBruta;
	/** Importe de prima no consumida*/
	private Double impPrimaNoCons;
}
