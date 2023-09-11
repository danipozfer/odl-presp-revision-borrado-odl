package com.santalucia.cdc.core.domain.policy.metadata;

import lombok.Data;

import java.util.List;

/**
 * Metadata
 *
 * @author Nfq
 *
 */
@Data
public class MetadataDomain {

	/** Numero del ultimo usuario */
	String numUltUsuario;
	/** Fecha SQL*/
	String fecSQL;
	/** Fecha recuperacion informacion no persistida*/
	String fecNoPersistida;
	/** Fecha y hora de registro de los datos*/
	String fecHoraRegistro;
	/** Fecha hasta que tiene validez los datos de la poliza*/
	String fecFinDatos;
	/** Version del esquema*/
	String version;
	/** Valor hash */
	String hash;
	/** Indicador borrado*/
	String indBorrado;
	/** Circuito origen*/
	String circuitoOrigen;
	/** Datos adicionales*/
	List<DatoAdiccionalDomain> datosAdicionales;

	/**
	 * Constructor de clase
	 */
	public MetadataDomain() {
		hash = "";
		version = "1";
		numUltUsuario = null;
		fecSQL = null;
		fecNoPersistida = null;
		fecHoraRegistro = null;
		fecFinDatos = null;
		indBorrado = null;
		circuitoOrigen = null;
		datosAdicionales = null;
	}
}
