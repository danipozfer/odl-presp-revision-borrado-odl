package com.santalucia.cdc.core.domain.policy.fechasestados;


import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Historico
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class HistoricoDomain {

	/** Codigo del tipo del estado de la poliza*/
	private String codTipoEstPoliza;
	/** Denominacion del tipo del estado de la poliza*/
	private String descTipoEstPoliza;
	/** Fecha de estado de la poliza*/
	private String fecEstadoPol;
	/** Codigo de la causa del estado de la poliza*/
	private String codCausEstPol;
	/** Denominacion de la causa del estado de la poliza*/
	private String descCausEstPol;
	/** Codigo del subestado de la poliza*/
	private String codSubEstPol;
	/** Descripcion del subestado de la poliza*/
	private String descSubEstPol;
	/** Fecha de subestado de la poliza*/
	private String fecSubEstPol;
	/** Numero de orden de movimiento de estado/subestado*/
	private Integer numOrdenMovEst;

}
