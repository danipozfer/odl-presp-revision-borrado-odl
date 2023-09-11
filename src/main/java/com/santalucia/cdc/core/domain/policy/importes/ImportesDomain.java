package com.santalucia.cdc.core.domain.policy.importes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Importe
 *
 * @author Nfq
 *
 */
@Data
public class ImportesDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Codigo de moneda*/
	String codMoneda;
	/** Importe total recibo*/
	Double impTotalRecibo;
	/** Nivel de polizas*/
	List<NivelPolizaDomain> poliza;

	/**
	 * Constructor de clase
	 */
	public ImportesDomain() {
		poliza = new ArrayList<>(DEFAULT_CAPACITY);
		impTotalRecibo = null;
		codMoneda = null;
	}

}
