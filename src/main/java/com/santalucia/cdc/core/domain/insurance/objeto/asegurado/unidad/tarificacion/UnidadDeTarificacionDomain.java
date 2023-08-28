package com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion;

import com.santalucia.cdc.core.domain.insurance.objeto.asegurado.unidad.tarificacion.capitales.CapitalesDomain;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * UnidadDeTarificacion
 *
 * @author Nfq
 *
 */
@Data
@NoArgsConstructor
public class UnidadDeTarificacionDomain {
	/**
     * Capacidad inicial por defecto
     */
    private static final int DEFAULT_CAPACITY = 10;

	private String codMoneda;
	/** */
	private String codNivelTarif;
	/** */
	private String codTipPrima;
	/** */
	private String descNivelTarif;
	/** */
	private String descTipPrima;
	/** */
	private String idNivelTarif;
	/** */
	private DatosIdentificativosTarifDomain datosIdentificativosUnidadTarif = new DatosIdentificativosTarifDomain();
	/** */
	private FechasTarifDomain fechasUnidadTarif = new FechasTarifDomain();
	/** */
	private DatosPropiosTarifDomain datosPropios = new DatosPropiosTarifDomain();
	/** */
	private PrimasTarifDomain primasUnidadTarif = new PrimasTarifDomain();
	/** */
	private CapitalesDomain capitales = new CapitalesDomain();
	/** */
	private List<SobreprimasTarifDomain> sobreprimasUnidadTarif = new ArrayList<>(DEFAULT_CAPACITY);
	/** */
	private List<BonificacionesDescuentosTarifDomain> bonificacionesDescuentos = new ArrayList<>(DEFAULT_CAPACITY);
	/** */
	private List<ComposicionGarantiasTarifDomain> composicionGarantias = new ArrayList<>(DEFAULT_CAPACITY);

}
