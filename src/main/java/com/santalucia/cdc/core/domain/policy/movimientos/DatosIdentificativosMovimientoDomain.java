package com.santalucia.cdc.core.domain.policy.movimientos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Datos Identificativos Movimiento
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DatosIdentificativosMovimientoDomain {

	/** Identificador de la poliza ODL */
	private String idPolizaODL;
	/** Identificador de la poliza origen */
	private String idPolizaOrigen;
	/** Numero de certificado */
	private String numCertificado;
	/** Codigo de tipo de poliza */
	private String codTipPoliza;

}
