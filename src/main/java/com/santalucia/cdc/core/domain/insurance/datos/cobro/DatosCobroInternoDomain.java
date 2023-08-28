package com.santalucia.cdc.core.domain.insurance.datos.cobro;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Objeto Datos Cobro
 *
 * @author Nfq
 *
 */

@Data
@NoArgsConstructor
public class DatosCobroInternoDomain {

	/** Codigo cobro/pago interno compania */
	private String codCobroInterno;
	/** Denominacion cobro/pago interno compania */
	private String descCobroInterno;
}
