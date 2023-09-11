package com.santalucia.cdc.core.domain.policy.datos.cobro;

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
public class DatosCobroOtrosMediosDomain {

	/** Codigo de clave de datos de medio de cobro/pago */
	private String codDatosMedCob;
	/** Denominacion de clave de datos de medio de cobro/pago */
	private String descDatosMedCob;
	/** Valor de datos de medio de cobro/pago */
	private String valorDatosMedCob;

}
