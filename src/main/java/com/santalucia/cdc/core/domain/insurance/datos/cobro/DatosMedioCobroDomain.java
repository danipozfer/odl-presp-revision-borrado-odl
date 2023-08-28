package com.santalucia.cdc.core.domain.insurance.datos.cobro;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Objeto Datos Cobro
 *
 * @author Nfq
 *
 */

@Data
@NoArgsConstructor
public class DatosMedioCobroDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Datos Cobro Bancario */
	private DatosCobroBancarioDomain datosCobroBancario = new DatosCobroBancarioDomain();

	/** Datos Cobro Fisico */
	private DatosCobroFisicoDomain datosCobroFisico = new DatosCobroFisicoDomain();

	/** Datos Cobro Interno */
	private DatosCobroInternoDomain datosCobroInterno = new DatosCobroInternoDomain();

	/** Lista Datos Cobro Otros Medios*/
	private List<DatosCobroOtrosMediosDomain> datosCobroOtrosMedios = new ArrayList<>(DEFAULT_CAPACITY);

}
