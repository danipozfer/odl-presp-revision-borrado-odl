package com.santalucia.cdc.core.domain.policy.fechasestados;

import lombok.Data;

/**
 * FechasYEstados
 *
 * @author Nfq
 *
 */
@Data
public class FechasEstadosDomain {

	/** Fecha */
	FechaDomain fechas;
	/** Estado*/
	EstadoDomain estados;

	/**
	 * Constructor de clase
	 */
	public FechasEstadosDomain() {
		fechas = new FechaDomain();
		estados = new EstadoDomain();
	}

}
