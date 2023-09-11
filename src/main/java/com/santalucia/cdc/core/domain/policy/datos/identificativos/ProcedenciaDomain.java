package com.santalucia.cdc.core.domain.policy.datos.identificativos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PolizaDeProcedencia
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ProcedenciaDomain {

	/** Codigo de tipo de procedencia */
	private String codTipoProced;
	/** Denominacion del tipo de procedencia*/
	private String descTipoProced;
	/** Identificador unico de poliza de procedencia*/
	private String idPolizaProced;
}
