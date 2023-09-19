package com.santalucia.cdc.core.exceptions;

import com.santalucia.arq.ams.componentes.exceptions.core.SantaluciaCoreBusinessException;
import com.santalucia.cdc.core.exceptions.errors.AppErrorCodes;

/**
 * Excepcion producida al calcular el hash de una poliza ODL
 * @author Nfq
 *
 */
@SuppressWarnings("NullAway")
public class HashGenerationException extends SantaluciaCoreBusinessException {

	private static final long serialVersionUID = -4839425922066569485L;

	/**
	 * constructor de clase
	 *
	 * @param Throwable cause
	 */
	public HashGenerationException(Throwable cause) {
		super(AppErrorCodes.HASH_GENERATION, cause);
	}

	/**
	 * constructor de clase
	 *
	 */
	public HashGenerationException() {
		this(null);
	}
}
