package com.santalucia.cdc.core.domain.policy.importes;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SobrePrima
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class SobrePrimaDomain {

	/** Codigo de Sobreprima a Nivel de Poliza*/
	private String codSobreprima;
	/** Denominacion de Sobreprima a Nivel de Poliza*/
	private String descSobreprima;
	/** Importe de Sobreprima a Nivel de Poliza*/
	private Double impSobreprima;

}
