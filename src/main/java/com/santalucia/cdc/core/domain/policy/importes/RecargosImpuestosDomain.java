package com.santalucia.cdc.core.domain.policy.importes;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RecargoEImpuesto
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class RecargosImpuestosDomain {

	/** Codigo de Impuesto o Recargo a Nivel de Poliza*/
	private String codRecargoImp;
	/** Denominacion de Impuesto o Recargo a Nivel de Poliza*/
	private String descRecargoImp;
	/** Importe de Impuesto o Recargo a Nivel de Poliza*/
	private Double impRecargoImp;

}
