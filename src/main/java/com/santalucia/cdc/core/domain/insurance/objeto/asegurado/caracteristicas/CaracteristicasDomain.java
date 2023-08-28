package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.caracteristicas;

import com.santalucia.cdc.core.domain.insurance.estructura.geografica.DomicilioDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * ObjetoAsegurado
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class CaracteristicasDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	private List<DomicilioDomain> domicilioObjAseg = new ArrayList<>(DEFAULT_CAPACITY);
	private List<FiguraDomain> figura = new ArrayList<>(DEFAULT_CAPACITY);
	private List<AnimalesDomain> animales = new ArrayList<>(DEFAULT_CAPACITY);
	private List<DisposicionesElectronicasDomain> dispositivosElectronicos = new ArrayList<>(DEFAULT_CAPACITY);

}
