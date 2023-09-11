package com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion.capitales;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Previsiones
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class PrevisionesDomain {

	/** Ano del seguro */
	private Integer annoSeguro;
	/** Importe de capital */
	private Double impCapital;

}
