package com.santalucia.cdc.core.domain.policy.polizas;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Producto
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ProductoDomain {

	/** Codigo de ramo*/
	private String codRamo;
	/** Denominacion de ramo*/
	private String descRamo;
	/** Codigo de producto tecnico*/
	private String codProductoTecnico;
	/** Denominacion del producto tecnico*/
	private String descProductoTecnico;
	/** Codigo del producto comercial*/
	private String codProducto;
	/** Denominacion del producto comercial*/
	private String descProducto;
	/** Codigo de modalidad comercial*/
	private String codModalidad;
	/** Denominacion de la modalidad comercial*/
	private String descModalidad;
	/** Denominacion de la modalidad interna*/
	private String descModalidadInter;

}
