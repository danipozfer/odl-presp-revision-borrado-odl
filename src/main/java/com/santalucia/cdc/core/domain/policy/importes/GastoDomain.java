package com.santalucia.cdc.core.domain.policy.importes;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Gasto
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class GastoDomain {

	/** Codigo de Tipo de Gasto a Nivel de Poliza*/
	private String codTipoGasto;
	/** Denominacion de Tipo de Gasto a Nivel de Poliza*/
	private String descTipoGasto;
	/** Codigo de Gasto a Nivel de Poliza*/
	private String codGasto;
	/** Denominacion de Gasto a Nivel de Poliza*/
	private String descGasto;
	/** Importe de Gasto a Nivel de Poliza*/
	private Double impGasto;

}
