package com.santalucia.cdc.core.domain.policy.hash;

import com.santalucia.cdc.core.domain.policy.coaseguro.CoaseguroDomain;
import com.santalucia.cdc.core.domain.policy.datos.cobro.DatosCobroDomain;
import com.santalucia.cdc.core.domain.policy.datos.identificativos.DatosIdentificativosDomain;
import com.santalucia.cdc.core.domain.policy.estructura.geografica.EstructuraGeograficaDomain;
import com.santalucia.cdc.core.domain.policy.figuras.FigurasDomain;
import com.santalucia.cdc.core.domain.policy.importes.ImportesDomain;
import com.santalucia.cdc.core.domain.policy.polizas.*;
import com.santalucia.cdc.core.domain.policy.reaseguro.ReaseguroDomain;
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
public class PolizaHashDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Datos identificativos*/
	DatosIdentificativosDomain datosIdentificativos;
	/** Producto*/
	ProductoDomain producto;
	/** Fechas y estados*/
	FechasEstadosHashDomain fechasYEstados;
	/** Subcolectivos */
	List<SubColectivosDomain> subcolectivos;
	/** Datos cobro **/
	DatosCobroDomain datosCobro;
	/** Documentos */
	List<DocumentosDomain> documentos;
	/** Figuras */
	FigurasDomain figuras;
	/** Estructura comercial*/
	EstructuraComercialDomain estructuraComercial;
	/** Estructura geografica*/
	EstructuraGeograficaDomain estructuraGeografica;
	/** Importes */
	ImportesDomain importes;
	/** Campanas */
	List<CampanasDomain> campanas;
	/** Reaseguro */
	ReaseguroDomain reaseguro;
	/** Coaseguro */
	CoaseguroDomain coaseguro;
	/** Movimientos */
	MovimientosDomain movimientos;
	/** ClausulasGDPR */
	List<ClausulasGDPRDomain> clausulasGDPR;

	/**
	 * Constructor de clase
	 */
	public PolizaHashDomain() {
		datosIdentificativos = new DatosIdentificativosDomain();
		producto = new ProductoDomain();
		fechasYEstados = new FechasEstadosHashDomain();
		subcolectivos = new ArrayList<>(DEFAULT_CAPACITY);
		datosCobro = new DatosCobroDomain();
		documentos = new ArrayList<>(DEFAULT_CAPACITY);
		figuras = new FigurasDomain();
		estructuraComercial = new EstructuraComercialDomain();
		estructuraGeografica = new EstructuraGeograficaDomain();
		importes = new ImportesDomain();
		campanas = new ArrayList<>(DEFAULT_CAPACITY);
		reaseguro = new ReaseguroDomain();
		coaseguro = new CoaseguroDomain();
		movimientos = new MovimientosDomain();
		clausulasGDPR = new ArrayList<>(DEFAULT_CAPACITY);
	}

}
