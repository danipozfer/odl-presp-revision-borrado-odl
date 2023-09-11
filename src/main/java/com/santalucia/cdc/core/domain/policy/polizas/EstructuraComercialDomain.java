package com.santalucia.cdc.core.domain.policy.polizas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * EstructuraComercial
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class EstructuraComercialDomain {

	/** Codigo del canal de mediacion */
	private String codCanal;
	/** Denominacion del canal de mediacion*/
	private String descCanal;
	/** Codigo del medio de distribucion*/
	private String codMedioDist;
	/** Denominacion del medio de distribucion*/
	private String descMedioDist;
	/** Codigo de agencia*/
	private String codAgencia;
	/** Denominacion de agencia*/
	private String descAgencia;
	/** Codigo de zona*/
	private String codZona;
	/** Denominacion de zona*/
	private String descZona;
	/** Codigo de distrito*/
	private String codDistrito;
	/** Denominacion de distrito*/
	private String descDistrito;
	/** Codigo de oficina*/
	private String codOficina;
	/** Denominacion de oficina*/
	private String descOficina;
	/** Codigo de mediador*/
	private String codMediador;
	/** Codigo de tipo de mediador*/
	private String codTipoMediador;
	/** Denominacion de tipo de mediador*/
	private String descTipoMediador;
	/** Codigo de tipo de colaborador*/
	private String codTipoColab;
	/** Denominacion de tipo de colaborador*/
	private String descTipoColab;
	/** Codigo de colaborador*/
	private String codColab;
	/** Codigo de tipo de colaborador inicial*/
	private String codTipoColabIni;
	/** Denominacion de tipo de colaborador inicial*/
	private String descTipoColabIni;
	/** Codigo de colaborador inicial*/
	private String codColabIni;
	/** Codigo de monitor*/
	private String codMonitor;
	/** Codigo de monitor inicial*/
	private String codMonitorIni;
	/** Codigo de red de venta */
	private String codRedVenta;
	/** Denominacion de red de venta */
	private String descRedVenta;
}
