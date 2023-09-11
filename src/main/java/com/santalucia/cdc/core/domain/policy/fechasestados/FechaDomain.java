package com.santalucia.cdc.core.domain.policy.fechasestados;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Fecha
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class FechaDomain {

	/** Fecha de alta de la poliza*/
	private String fecAltaPoliza;
	/** Fecha de registro de la poliza*/
	private String fecRegistPoliza;
	/** Fecha de efecto de la poliza*/
	private String fecEfectoPoliza;
	/** Fecha pendiente de emision de la poliza*/
	private String fecPdteEmiPoliza;//NO SE ENCUENTRA SE ENCUENTRA AHORA
	/** Fecha de emision de la poliza*/
	private String fecEmisionPoliza;//NO SE ENCUENTRA SE ENCUENTRA AHORA
	/** Fecha de tarificacion*/
	private String fecTarificacion;//NO SE ENCUENTRA SE ENCUENTRA AHORA
	/** Fecha de formalizacion de la poliza*/
	private String fecFormalizacionPol;//NO SE ENCUENTRA // SE AnADIRA A POSTERIORI
	/** Fecha de liquidacion de la poliza*/
	private String fecLiquiPoliza;
	/** Fecha de extincion de la poliza*/
	private String fecExtincionPol;
	/** Fecha de ultimo vencimiento de la poliza*/
	private String fecUltVencPol;
	/** Fecha de proximo vencimiento de la poliza*/
	private String fecProxVencPol;
	/** Fecha efecto de comisionamiento*/
	private String fecEfectoComisionPol;
	/** Fecha cese del pago de prima*/
	private String fecCesePagoPrima;//NO SE ENCUENTRA SE ENCUENTRA AHORA
	/**Fecha de vencimiento teorica*/
	private String fecVencimientoTeorica;
	/** Fecha de rvalorizacion **/
	private String fecRevalorizacion;

}
