package com.santalucia.cdc.core.domain.policy.coaseguro;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CoaseguroDomain
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class CoaseguroDomain {

	/** Indicador de tipo de coaseguro */
	private String indTipCoaseguro;

	private List<DatosPropiosDomain> datosPropios;
}
