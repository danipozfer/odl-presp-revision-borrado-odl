package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion;

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
public class DatosPropiosTarifDomain {

	/** Codigo de ramo contable */
	private String codRamContable;
	/** Denominacion de ramo contable */
	private String descRamContable;
	/** Codigo de modalidad contable */
	private String codModContable;
	/** Denominacion de modalidad contable*/
	private String descModContable;

}
