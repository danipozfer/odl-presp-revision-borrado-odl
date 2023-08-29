package com.santalucia.cdc.core.exceptions;

import com.santalucia.arq.ams.componentes.exceptions.core.SantaluciaCoreBusinessException;
import com.santalucia.cdc.core.exceptions.errors.AppErrorCodes;

/**
 * Excepcion al no encontrar una poliza en el API
 * @author Nfq
 *
 */
public class PolicyNotFoundException extends SantaluciaCoreBusinessException {

	private static final long serialVersionUID = -4839425922066569485L;

	/**
	 * constructor de clase
	 *
	 * @param policyId id de poliza no encontrada
	 */
	public PolicyNotFoundException(String policyId) {
		super(AppErrorCodes.POLICY_NOT_FOUND, policyId, null);
	}

	/**
	 * constructor de clase
	 *
	 */
	public PolicyNotFoundException() {
		this(null);
	}
}
