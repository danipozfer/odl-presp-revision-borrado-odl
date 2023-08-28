package com.santalucia.cdc.core.domain.insurance.estructura.geografica;

import lombok.Data;

/**
 * EstructuraGeografica
 *
 * @author Nfq
 *
 */
@Data
public class EstructuraGeograficaDomain {

	/** Domicilio */
	DomicilioDomain domicilio;

	/** Coordenadas*/
	CoordenadasDomain coordenadas;

	/**
	 * Constructor de clase
	 */
	public EstructuraGeograficaDomain() {
		coordenadas = new CoordenadasDomain();
		domicilio = new DomicilioDomain();
	}

}
