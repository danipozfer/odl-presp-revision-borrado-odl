package com.santalucia.cdc.core.domain.insurance.hash;

import com.santalucia.cdc.core.domain.insurance.fechasestados.EstadoDomain;
import lombok.Data;

/**
 * FechasYEstados
 *
 * @author Nfq
 *
 */
@Data
public class FechasEstadosHashDomain {

	/** Fecha */
	FechaHashDomain fechas;
	/** Estado*/
	EstadoDomain estados;

	/**
	 * Constructor de clase
	 */
	public FechasEstadosHashDomain() {
		fechas = new FechaHashDomain();
		estados = new EstadoDomain();
	}

}
