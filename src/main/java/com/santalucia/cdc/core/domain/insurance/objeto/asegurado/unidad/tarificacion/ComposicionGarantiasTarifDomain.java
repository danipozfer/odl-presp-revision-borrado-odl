package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ComposicionGarantiasTarif
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ComposicionGarantiasTarifDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Codigo de garantia */
	private String codGarantia;
	/** Descripcion de garantia */
	private String descGarantia;
	/** */
	private List<ComposicionSubgarantiasDomain> composicionSubgarantias = new ArrayList<>(DEFAULT_CAPACITY);

}
