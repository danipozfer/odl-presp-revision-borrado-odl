package com.santalucia.cdc.core.domain.policy.fechasestados;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Estado
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class EstadoDomain {

	/** Indicador de la poliza formalizada */
	private String indPolFormalizada;
	/** Estado actual de la poliza */
	private String codTipoEstPolizaAct;
	/** Fecha estado actual de la poliza */
	private String fecEstadoPolAct;
	/** Fecha prevista de la proxima anulacion*/
	private String fecPrevProxAnul;
	/** Fecha prevista de la proxima reduccion*/
	private String fecPrevProxRed;
	/** Historico*/
	private List<HistoricoDomain> historico;

}
