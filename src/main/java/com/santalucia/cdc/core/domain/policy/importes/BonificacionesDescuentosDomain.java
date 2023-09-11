package com.santalucia.cdc.core.domain.policy.importes;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BonificacionYDescuento
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class BonificacionesDescuentosDomain {

	/** Codigo de bonificacion y descuento*/
	private String codBonificacionDesc;
	/** Descripcion de bonificacion y descuento*/
	private String descBonificacionDesc;
	/** Importe de bonificacion y descuento*/
	private Double impBonificacionDesc;

}
