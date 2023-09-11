package com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion;

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
public class DatosIdentificativosTarifDomain {

	/** Codigo categoria */
	private String codCategoria;
	/** Denominacion de categoria */
	private String descCategoria;
	/** Codigo del tipo de complemento */
	private String codTipComplem;
	/** Denominacion del tipo de complemento */
	private String descTipComplem;
	/** Numero de orden del complemento */
	private String numOrdComplem;
	/** Codigo de agrupacion de garantias */
	private String codAgrupGarantia;
	/** Denominacion de agrupacion de garantias */
	private String descAgrupGarantia;
	/** Numero de orden de agrupacion de garantia */
	private String numOrdenAgrupGarantia;
	/** Agrupacion de garantias-hogar */
	private String agrupGarantiaHog;
	/** Codigo de la garantia unitaria */
	private String codGarantiaUnit;
	/** Denominacion de la garantia unitaria */
	private String descGarantiaUnit;
	/** Numero de Orden Persona/Rol */
	private String numOrdenPersRol;
	/** Codigo del riesgo */
	private String codRiesgo;
	/** Denominacion del riesgo */
	private String descRiesgo;
	/** Edad de tarificacion */
	private String edadTarif;
}
