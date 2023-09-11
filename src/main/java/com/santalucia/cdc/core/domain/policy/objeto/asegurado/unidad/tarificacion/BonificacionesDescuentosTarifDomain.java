package com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BonificacionesDescuentosTarif
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class BonificacionesDescuentosTarifDomain {

	/** Codigo de Bonificacion o Descuento a Nivel de Tarificacion */
	private String codBonificacion;
	/** Denominacion de Bonificacion o Descuento a Nivel de Tarificacion */
	private String descBonificacion;
	/** Importe de Bonificacion o Descuento a Nivel de Tarificacion */
	private Double impBonificacion;
	/** Tasa de Bonificacion o Descuento a Nivel de Tarificacion */
	private String tasBonificacion;
	/** Tipo de Calculo de Bonificacion o Descuento a Nivel de Tarificacion */
	private String tipCalBonificacion;

}
