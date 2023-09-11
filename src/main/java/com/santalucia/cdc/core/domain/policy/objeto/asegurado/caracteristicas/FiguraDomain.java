package com.santalucia.cdc.core.domain.policy.objeto.asegurado.caracteristicas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Figura
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class FiguraDomain {

	/** Numero de identificacion de persona */
	private String idPersona;
	/** Codigo de tipo de persona */
	private String codTipPersona;
	/** Denomiancion de tipo de persona */
	private String descTipPersona;
	/** Nombre de la persona */
	private String nombre;
	/** Primer apellido de la persona */
	private String primerApellido;
	/** Segundo apellido de la persona */
	private String segundoApellido;
	/** Razon social */
	private String razonSocial;
	/** Codigo de tipo de documento de identificacion */
	private String codTipDocumento;
	/** Denominacion de tipo de documento de identificacion */
	private String descTipDocumento;
	/** Numero de documento de identificacion */
	private String numDocumento;
	/** Fecha de nacimiento */
	private String fecNacimiento;
	/** Codigo de sexo de la persona */
	private String codSexo;
	/** Denominacion de sexo de la persona */
	private String descSexo;
	/** Codigo de nacionalidad */
	private String codNacional;
	/** Denominacion de nacionalidad */
	private String descNacional;

}
