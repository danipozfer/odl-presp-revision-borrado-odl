package com.santalucia.cdc.core.domain.policy.datos.identificativos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DatoIdentificativo
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DatosIdentificativosDomain {

	/** Identificador unico de la poliza*/
	private String idPolizaODL;
	/** Certificado de colectivos*/
	private String numCertificado;
	/** Codigo de tipo de poliza*/
	private String codTipoPoliza;
	/** Denominacion del tipo de poliza*/
	private String descTipoPoliza;
	/** Codigo de companyia de origen*/
	private String codCiaOrigen;
	/** Denominacion de companyia de origen*/
	private String descCiaOrigen;
	/** Codigo del sistema de origen */
	private String codSistOrigen;
	/** Denominacion del sistema de origen*/
	private String descSistOrigen;
	/** Codigo del sistema actual*/
	private String codSistActual;
	/** Denominacion del sistema actual*/
	private String descSistActual;
	/** Identificador de la poliza (interno aplicacion)*/
	private String idPolizaOrigen;
	/** Numero de poliza*/
	private String numPoliza;
	/** Codigo de colectivo */
	private String codColect;
	/** Denominacion del colectivo*/
	private String descColect;
	/** Codigo de tipo de contrato*/
	private String codTipoContr;
	/** Denominacion de tipo de contrato*/
	private String descTipoContr;
	/** Numero de agrupacion de polizas (pack comercial)*/
	private String numPackPoliza;
	/** Numero de paquete*/
	private String numPaquete;
	/** Numero de identificacion de presupuesto*/
	private String numIdPresupuesto;
	/** Identificador de subcolectivo*/
	private String idSubcolectivo;
	/** Descripcion del subcolectivo*/
	private String descSubcolectivo;
	/** Identificador del tipo de comision*/
	private String codTipComision;
	/** Descripcion del tipo de comision*/
	private String descTipComision;
	/** Indicador nivel de datos colectivos*/
	private String indNivDatColect;
	/** Indicador cobertura prestaciones*/
	private String indCobertPrest;
	/** Polizas de procedencia*/
	private List<ProcedenciaDomain> procedencia;
	/** Polizas de destino*/
	private List<DestinoDomain> destino;
}
