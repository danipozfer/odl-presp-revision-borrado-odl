package com.santalucia.cdc.core.domain.insurance.hash;

import com.santalucia.cdc.core.domain.insurance.objeto.asegurado.DatosIdentificativosOAseguradoDomain;
import com.santalucia.cdc.core.domain.insurance.objeto.asegurado.caracteristicas.CaracteristicasDomain;
import com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion.UnidadDeTarificacionDomain;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Colleccion ObjetoAsegurado
 *
 * @author Nfq
 *
 */
@Data
@AllArgsConstructor
public class ObjetoAseguradoHashDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	DatosIdentificativosOAseguradoDomain datosIdentificativosObjAseg = new DatosIdentificativosOAseguradoDomain();
	CaracteristicasDomain caracteristicas = new CaracteristicasDomain();
	List<UnidadDeTarificacionDomain> unidadTarificacion = new ArrayList<>(DEFAULT_CAPACITY);

	/**
	 * Constructor de clase
	 */
	public ObjetoAseguradoHashDomain() {
		datosIdentificativosObjAseg = new DatosIdentificativosOAseguradoDomain();
		caracteristicas = new CaracteristicasDomain();
		unidadTarificacion = new ArrayList<>(DEFAULT_CAPACITY);
	}
}
