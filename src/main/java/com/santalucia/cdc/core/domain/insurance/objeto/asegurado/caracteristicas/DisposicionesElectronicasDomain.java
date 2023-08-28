package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.caracteristicas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DisposicionesElectronicas
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DisposicionesElectronicasDomain {

	/** Codigo Marca de Dispositivo Electronico */
	private String codMarcDispElect;
	/** Codigo Modelo de Dispositivo Electronico */
	private String codModDispElect;
	/** Codigo Identificador Dispositivo Electronico */
	private String codIdentDispElect;

}
