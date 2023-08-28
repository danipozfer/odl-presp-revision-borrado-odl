package com.santalucia.cdc.core.domain.insurance.datos.cobro;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Objeto Datos Cobro
 *
 * @author Nfq
 *
 */

@Data
@NoArgsConstructor
public class DatosCobroFisicoDomain {

	/** Indicador del tipo de domicilio de cobro/pago */
	private String indTipDomicCobro;
	/** Denominacion del tipo de domicilio de cobro/pago */
	private String descTipDomicCobro;
	/** Codigo de pais */
	private String codPais;
	/** Denominacion del pais */
	private String descPais;
	/** Codigo de provincia */
	private String codProvincia;
	/** Denominacion de provincia */
	private String descProvincia;
	/** Codigo de localidad */
	private String codLocalidad;
	/** Denominacion de localidad */
	private String descLocalidad;
	/** Codigo de municipio */
	private String codMunicipio;
	/** Codigo Postal */
	private String codPostal;
	/** Codigo de entidad colectiva */
	private String codEntColectiva;
	/** Codigo de entidad singular */
	private String codEntSingular;
	/** Codigo de nucleo de poblacion */
	private String codNucPobla;
	/** Denominacion de la poblacion */
	private String descPoblacion;
	/** Codigo del tipo de via */
	private String codTipVia;
	/** Denominacion tipo de via */
	private String descTipVia;
	/** Denominacion de domicilio */
	private String descDomicilio;
	/** Numero del domicilio */
	private Integer numDomicilio;
	/** Complemento del Numero del domicilio */
	private String compNumDomic;
	/** Bloque del domicilio */
	private String bloqueDomic;
	/** Portal del domicilio */
	private String portalDomic;
	/** Escalera del domicilio */
	private String escalDomic;
	/** Piso del domicilio */
	private String pisoDomic;
	/** Puerta del domicilio */
	private String puertaDomic;
	/** Otros datos de la direccion del domicilio */
	private String otrosDatosDomic;

}
