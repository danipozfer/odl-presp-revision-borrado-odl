package com.santalucia.cdc.core.domain.policy.polizas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClausulasGDPR
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ClausulasGDPRDomain {

	/** Codigo de marca GDPR */
	private String codMarcaGDPR;
	/** Denominacion de marca GDPR */
	private String descMarcaGDPR;
	/** Valor de marca GDPR */
	private String valorMarcaGDPR;
	/** Valor de marca GDPR */
	private String fecMarcaGDPR;
}
