package com.santalucia.cdc.core.domain.insurance.metadata;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DatoAdiccional
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DatoAdiccionalDomain {

	/** Descripcion metadata*/
	private String descMetadata;

	/** Valor metadata*/
	private String valorMetadata;
}