package com.santalucia.cdc.core.exceptions;

import com.santalucia.arq.ams.componentes.exceptions.core.SantaluciaCoreBusinessException;
import com.santalucia.cdc.core.exceptions.errors.AppErrorCodes;

/**
 * Excepcion al no encontrar una poliza en el API
 * @author Nfq
 *
 */
public class PolicyHistoricStateException extends SantaluciaCoreBusinessException {
	/**
	 *
	 */
	private static final long serialVersionUID = 8108968819761766464L;

	/**
	 * Constructor
	 *
	 * @param policyId id poliza
	 */
	public PolicyHistoricStateException(String policyId) {
		super(AppErrorCodes.POLICY_HISTORIC_STATE_ERROR, policyId, null);
	}
}
