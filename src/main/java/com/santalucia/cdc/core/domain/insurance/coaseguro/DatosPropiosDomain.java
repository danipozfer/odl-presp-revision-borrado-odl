package com.santalucia.cdc.core.domain.insurance.coaseguro;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DatosPropios
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class DatosPropiosDomain {

	/** Codigo de la entidad de coaseguro */
	private Integer numIdentCoaseg;
	/** Porcentaje participacion coaseguro */
	private String porcPartCoaseguro;
	/** Numero de la poliza de otra compania */
	private String numPolOtrComp;
	/** Porcentaje gastos administracion */
	private String porcGastAdmin;
	/** Porcentaje gastos comision sobre prima N */
	private String porcGastComPrimN;
	/** Porcentaje consorcio */
	private String porcConsorc;
	/** Porcentaje comision recargo externo */
	private String porcComRecExter;
	/** Porcentaje gastos por siniestro */
	private String porcGastSinies;
	/** Porcentaje comision */
	private String porcComision;
	/** Tipo de mediador */
	private String tipMediador;
	/** Codigo mediador */
	private String codMediador;
	/** Porcentaje impuestos Dgs */
	private Double porcImpDgs;
	/** Porcentaje impuesto sobre primas de seguros */
	private Double porcImpPrimSeg;
	/** Porcentaje recargo externo */
	private String porcRecarExterno;
	/** Porcentaje arbitrio bomberos */
	private String porcArbitBom;
	/** Porcentaje bonificacion por no siniestralidad */
	private String porcBonNoSinies;
}
