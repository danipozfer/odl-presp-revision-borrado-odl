package com.santalucia.cdc.core.domain.policy.objeto.asegurado;

import com.santalucia.cdc.core.domain.policy.metadata.MetadataDomain;
import com.santalucia.cdc.core.domain.policy.objeto.asegurado.caracteristicas.CaracteristicasDomain;
import com.santalucia.cdc.core.domain.policy.objeto.asegurado.unidad.tarificacion.UnidadDeTarificacionDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Colleccion ObjetoAsegurado
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class ColObjetoAseguradoDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	private DatosIdentificativosOAseguradoDomain datosIdentificativosObjAseg = new DatosIdentificativosOAseguradoDomain();
	private CaracteristicasDomain caracteristicas = new CaracteristicasDomain();
	private List<UnidadDeTarificacionDomain> unidadTarificacion = new ArrayList<>(DEFAULT_CAPACITY);
	private MetadataDomain metadata = new MetadataDomain();
}
