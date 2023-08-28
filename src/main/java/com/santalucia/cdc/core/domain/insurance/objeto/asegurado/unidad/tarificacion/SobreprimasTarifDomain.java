package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UnidadDeTarificacion
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class SobreprimasTarifDomain {

	/** Codigo de Sobreprima a Nivel de Tarificacion */
	private String codSobreprima;
	/** Denominacion de Sobreprima a Nivel de Tarificacion */
	private String descSobreprima;
	/** Importe de Sobreprima a Nivel de Tarificacion */
	private Double impSobreprima;
	/** Tasa de Sobreprima a Nivel de Tarificacion */
	private String tasSobreprima;
	/** Tipo de Calculo de Sobreprima a Nivel de Tarificacion */
	private String tipCalSobreprima;
}
