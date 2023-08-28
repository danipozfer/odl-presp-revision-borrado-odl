package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ComposicionSubgarantias
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ComposicionSubgarantiasDomain {

	/** Codigo de subgarantia */
	private String codSubgarantia;
	/** Descripcion de subgarantia */
	private String descSubgarantia;

}
