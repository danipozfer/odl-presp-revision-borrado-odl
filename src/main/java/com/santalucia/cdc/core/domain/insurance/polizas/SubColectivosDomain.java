package com.santalucia.cdc.core.domain.insurance.polizas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Subcolectivo
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class SubColectivosDomain {

	/** Id objeto asegurado*/
	private String idObjAsegODL;
	/** Codigo de subcolectivo */
	private String codSubcolect;
	/** Denominacion del subcolectivo*/
	private String descSubcolect;
	/** Numero de personas del subcolectivo*/
	private String numPersSubcolect;
	/** Numero de telefono del subcolectivo*/
	private String numTelefSubcolect;
}
