package com.santalucia.cdc.core.domain.policy.hash;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Dominio para la generacion del hash del objeto poliza.
 *
 * @author Nfq
 *
 */
@Data
@AllArgsConstructor
public class HashDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Poliza individual */
	PolizaHashDomain polizaIndividual;
	/** Objeto Asegurado */
	List<ObjetoAseguradoHashDomain> objetoAsegurado;
	/** Movimientos */
	MovimientoHashDomain movimientos;

	/**
	 * Constructor de clase
	 */
	public HashDomain() {
		polizaIndividual = new PolizaHashDomain();
		objetoAsegurado = new ArrayList<>(DEFAULT_CAPACITY);
		movimientos = new MovimientoHashDomain();
	}

}
