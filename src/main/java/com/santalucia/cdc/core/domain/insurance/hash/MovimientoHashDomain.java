package com.santalucia.cdc.core.domain.insurance.hash;

import com.santalucia.cdc.core.domain.insurance.movimientos.DatosIdentificativosMovimientoDomain;
import com.santalucia.cdc.core.domain.insurance.movimientos.UltimoMovimientoDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Dominio para la generacion del hash del objeto poliza.
 *
 * @author Nfq
 *
 */
@Data
@AllArgsConstructor
public class MovimientoHashDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Datos identificativos del movimiento */
	DatosIdentificativosMovimientoDomain datosIdentificativos;
	/** Ultimo Movimiento */
	UltimoMovimientoDomain ultimoMovimiento;
	/** Historico Movimientos */
	List<UltimoMovimientoDomain> historicoMovimiento;

	/**
	 * Constructor de clase
	 */
	public MovimientoHashDomain() {
		datosIdentificativos = new DatosIdentificativosMovimientoDomain();
		ultimoMovimiento = new UltimoMovimientoDomain();
		historicoMovimiento = new ArrayList<>(DEFAULT_CAPACITY);
	}

}
