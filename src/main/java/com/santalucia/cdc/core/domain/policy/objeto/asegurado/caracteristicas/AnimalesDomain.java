package com.santalucia.cdc.core.domain.policy.objeto.asegurado.caracteristicas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Animales
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class AnimalesDomain {

	/** Indicador Tipo de Especie Animal */
	private String indTipEspAnim;
	/** Codigo Raza Animal */
	private String codRazAnim;
	/** Indicador Tipo Identificacion Animal de Compania */
	private String indTipIdentAnimComp;
	/** Numero Identificacion Animal de Compania */
	private String numIdentAnimComp;
	/** Valor Edad */
	private String valEdad;

}
