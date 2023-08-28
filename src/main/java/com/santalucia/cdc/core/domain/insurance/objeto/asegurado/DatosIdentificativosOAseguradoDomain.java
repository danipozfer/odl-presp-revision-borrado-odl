package com.santalucia.cdc.core.domain.insurance.objeto.asegurado;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ObjetoAsegurado
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DatosIdentificativosOAseguradoDomain {

	private String codModServicio;
	private String codTipObjAseg;
	private String descTipObjAseg;
	private String idObjAsegODL;
	private String idPolizaODL;
	private String numCertificado;
	private String versPolizaODL;
	private String versPolizaOrigen;
	private String localTarif;
	private String numOrdenObjAseg;
	private String provTarif;

}
