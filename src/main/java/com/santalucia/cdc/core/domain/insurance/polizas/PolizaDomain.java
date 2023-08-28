package com.santalucia.cdc.core.domain.insurance.polizas;

import com.santalucia.cdc.core.domain.insurance.coaseguro.CoaseguroDomain;
import com.santalucia.cdc.core.domain.insurance.datos.cobro.DatosCobroDomain;
import com.santalucia.cdc.core.domain.insurance.datos.identificativos.DatosIdentificativosDomain;
import com.santalucia.cdc.core.domain.insurance.estructura.geografica.EstructuraGeograficaDomain;
import com.santalucia.cdc.core.domain.insurance.fechasestados.FechasEstadosDomain;
import com.santalucia.cdc.core.domain.insurance.figuras.FigurasDomain;
import com.santalucia.cdc.core.domain.insurance.importes.ImportesDomain;
import com.santalucia.cdc.core.domain.insurance.metadata.MetadataDomain;
import com.santalucia.cdc.core.domain.insurance.reaseguro.ReaseguroDomain;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Objeto poliza
 *
 * @author Nfq
 *
 */
@Data
public class PolizaDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	/** Datos identificativos*/
	DatosIdentificativosDomain datosIdentificativos;
	/** Producto*/
	ProductoDomain producto;
	/** Fechas y estados*/
	FechasEstadosDomain fechasYEstados;
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
	/** Metadata*/
	MetadataDomain metadata;
	/**Objetos asegurados*/
	List<ObjetoAseguradoPolizaDomain> objetosAsegurados;

	/**
	 * Constructor de clase
	 */
	public PolizaDomain() {
		datosIdentificativos = new DatosIdentificativosDomain();
		producto = new ProductoDomain();
		fechasYEstados = new FechasEstadosDomain();
		subcolectivos = new ArrayList<>(DEFAULT_CAPACITY);
		figuras = new FigurasDomain();
		estructuraComercial = new EstructuraComercialDomain();
		estructuraGeografica = new EstructuraGeograficaDomain();
		importes = new ImportesDomain();
		metadata = new MetadataDomain();
		campanas = new ArrayList<>(DEFAULT_CAPACITY);
		reaseguro = new ReaseguroDomain();
		coaseguro = new CoaseguroDomain();
		movimientos = new MovimientosDomain();
		clausulasGDPR = new ArrayList<>(DEFAULT_CAPACITY);
		datosCobro = new DatosCobroDomain();
		documentos = new ArrayList<>(DEFAULT_CAPACITY);
		objetosAsegurados = new ArrayList<>(DEFAULT_CAPACITY);
	}

}
