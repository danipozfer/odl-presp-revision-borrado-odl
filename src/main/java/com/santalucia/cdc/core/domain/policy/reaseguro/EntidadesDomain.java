package com.santalucia.cdc.core.domain.policy.reaseguro;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidades
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class EntidadesDomain {

	/** Codigo de identificador del reasegurador*/
	private String codIdenReaseg;
	/** Denominacion del reasegurador */
	private String descReaseg;
	/** Porcentaje de particion */
	private String porPartReaseg;
}
