package com.santalucia.cdc.core.domain.insurance.importes;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * NivelPoliza
 *
 * @author Nfq
 *
 */
@Data
public class NivelPolizaDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Codigo de tipo de prima*/
	String codTipoPrima;
	/** Denominacion de tipo de prima*/
	String descTipoPrima;
	/** Primas*/
	PrimaDomain primas;
	/** Sobre primas*/
	List<SobrePrimaDomain> sobreprimas;
	/** Bonificaciones y descuentos*/
	List<BonificacionesDescuentosDomain> bonificacionesYDescuentos;
	/** Recargos e impuestos*/
	List<RecargosImpuestosDomain> recargosEImpuestos;
	/** Gastos*/
	List<GastoDomain> gastos;

	/**
	 * Constructor de clase
	 */
	public NivelPolizaDomain() {
		sobreprimas = new ArrayList<>(DEFAULT_CAPACITY);
		bonificacionesYDescuentos = new ArrayList<>(DEFAULT_CAPACITY);
		recargosEImpuestos = new ArrayList<>(DEFAULT_CAPACITY);
		gastos = new ArrayList<>(DEFAULT_CAPACITY);
		codTipoPrima = null;
		descTipoPrima = null;
		primas = null;
	}
}
