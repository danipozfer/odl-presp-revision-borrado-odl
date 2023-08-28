package com.santalucia.cdc.core.domain.insurance.reaseguro;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Reaseguro
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ReaseguroDomain {

	private String codIndReaseg;
	private String codIndCedProp;
	private String codIndAcept;
	private List<ReaseguroCedidoProporcionalDomain> reaseguroCedidoProporcional;
	private ReaseguroAceptadoDomain reaseguroAceptado;

}
