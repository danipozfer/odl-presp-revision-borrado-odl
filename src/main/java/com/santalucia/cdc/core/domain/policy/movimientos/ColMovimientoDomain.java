package com.santalucia.cdc.core.domain.policy.movimientos;

import com.santalucia.cdc.core.domain.policy.metadata.MetadataDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto Movimiento
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ColMovimientoDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Datos identificativos del movimiento */
	private DatosIdentificativosMovimientoDomain datosIdentificativos = new DatosIdentificativosMovimientoDomain();
	/** Ultimo Movimiento */
	private UltimoMovimientoDomain ultimoMovimiento = new UltimoMovimientoDomain();
	/** Historico Movimientos */
	private List<UltimoMovimientoDomain> historicoMovimiento = new ArrayList<>(DEFAULT_CAPACITY);
	/** Metadata domain */
	private MetadataDomain metadata = new MetadataDomain();

}
