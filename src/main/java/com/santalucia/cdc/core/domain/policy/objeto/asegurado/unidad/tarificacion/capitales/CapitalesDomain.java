package com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion.capitales;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * CapitalesDomain
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class CapitalesDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Importe de capital a Nivel de tarificacion*/
	private Double impCapital;
	/** Importe de capital reducido*/
	private Double impCapitalRed;
	/** Importe de capital basico*/
	private Double impCapitalBasic;
	/** Porcentaje Incremento de Capital*/
	private String porcIncremCap;
	/** */
	private List<PrevisionesDomain> previsiones = new ArrayList<>(DEFAULT_CAPACITY);
}
