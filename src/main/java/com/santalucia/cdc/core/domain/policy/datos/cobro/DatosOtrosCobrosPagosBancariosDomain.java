package com.santalucia.cdc.core.domain.policy.datos.cobro;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Objeto Datos Cobro
 *
 * @author Nfq
 *
 */

@Data
@NoArgsConstructor
public class DatosOtrosCobrosPagosBancariosDomain {

	/** Indicador tipo domicilio bancario */
	private String indTipDomBanc;
	/** Denominacion tipo domicilio bancario */
	private String descTipDomBanc;
	/** Codigo de entidad bancaria */
	private String codEntBanc;
	/** Denominacion de entidad bancaria */
	private String descEntBanc;
	/** Numero de digito de control del numero de cuenta */
	private Integer numDigContrNumCuent;
	/** Numero de digito de control entidad/oficina */
	private Integer numDigContrEntidOfic;
	/** Numero de cuenta bancaria */
	private Long numCuentBanc;
	/** Titular de la cuenta bancaria */
	private String titulCuentBanc;
	/** Codigo IBAN */
	private String codIban;
	/** Codigo BIC */
	private String codBic;
}
