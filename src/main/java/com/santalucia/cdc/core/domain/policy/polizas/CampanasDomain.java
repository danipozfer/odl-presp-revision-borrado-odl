package com.santalucia.cdc.core.domain.policy.polizas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Objeto Campanas
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class CampanasDomain {

	/** Codigo de campana comercial */
	private String codCampCom;
	/** Denominacion de campana comercial */
	private String descCampCom;
	/** Codigo del incentivo de la campana */
	private String codIncentCamp;
	/** Denominacion del incentivo de la campana */
	private String descIncentCamp;
	/** Indicador de caracteristicas del incentivo */
	private String indCaracIncent;
	/** Denominacion del indicador de caracteristicas del incentivo */
	private String descIndCaracIncent;
	/** Fecha de inicio de la campana */
	private String fecInicCamp;
	/** Fecha de fin de campana */
	private String fecFinVigencia;
	/** Duracion anos aplicacion de la campana */
	private String durAnnoApliCamp;
}
