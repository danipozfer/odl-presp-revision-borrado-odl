package com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UnidadDeTarificacion
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class FechasTarifDomain {

	/** Fecha de efecto del complemento */
	private String fecEfectComplem;
	/** Fecha de tarificacion */
	private String fecTarificacion;
	/** Fecha de efecto de la agrupacion de garantias */
	private String fecEfectoAgrGarantias;
	/** Fecha de extincion*/
	private String fecExtincion;
	/** Fecha de inicio periodo*/
	private String fecInicPeriod;
	/** Fecha fin periodo V2 */
	private String fecFinPeriodV2;

}
