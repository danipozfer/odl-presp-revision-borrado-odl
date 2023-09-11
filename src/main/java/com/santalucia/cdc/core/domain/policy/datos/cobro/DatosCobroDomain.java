package com.santalucia.cdc.core.domain.policy.datos.cobro;

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
public class DatosCobroDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Codigo de nivel de cobro/pago */
	private String codNivCobPag;
	/** Denominacion de nivel de cobro/pago */
	private String descNivCobPag;
	/** Indicador de la forma de cobro/pago */
	private String indFormCobPag;
	/** Denominacion de la forma de cobro/pago */
	private String descFormCobPag;
	/** Codigo de medio de cobro/pago */
	private String codMedCobPag;
	/** Denominacion del medio de cobro/pago*/
	private String descMedCobPag;
	/** Datos medio cobro */
	private DatosMedioCobroDomain datosMedioCobro = new DatosMedioCobroDomain();
	/** Datos Otros Cobros Pagos Bancarios */
	private List<DatosOtrosCobrosPagosBancariosDomain> datosOtrosCobrosPagosBancarios = new ArrayList<>(DEFAULT_CAPACITY);
}
