package com.santalucia.cdc.core.domain.policy.datos.identificativos;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * PolizaDeDestino
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DestinoDomain {

	/** Codigo de tipo de destino*/
	private String codTipoDestino;
	/** Denominacion del tipo de destino*/
	private String descTipoDestino;
	/** Identificador unico de poliza de destino*/
	private String idPolizaDestino;
}
